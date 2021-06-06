package com.xuecheng.manage_cms.service;


import com.alibaba.fastjson.JSON;
import com.mongodb.client.gridfs.GridFSBucket;
import com.mongodb.client.gridfs.GridFSDownloadStream;
import com.mongodb.client.gridfs.model.GridFSFile;
import com.xuecheng.framework.domain.cms.CmsConfig;
import com.xuecheng.framework.domain.cms.CmsPage;
import com.xuecheng.framework.domain.cms.CmsTemplate;
import com.xuecheng.framework.domain.cms.request.QueryPageRequest;
import com.xuecheng.framework.domain.cms.response.CmsPageResult;
import com.xuecheng.framework.exception.CmsCode;
import com.xuecheng.framework.exception.ExceptionCast;
import com.xuecheng.framework.model.response.CommonCode;
import com.xuecheng.framework.model.response.QueryResponseResult;
import com.xuecheng.framework.model.response.QueryResult;
import com.xuecheng.framework.model.response.ResponseResult;
import com.xuecheng.manage_cms.conﬁg.RabbitmqConfig;
import com.xuecheng.manage_cms.dao.CmsConfigRepository;
import com.xuecheng.manage_cms.dao.CmsPageRespository;
import com.xuecheng.manage_cms.dao.CmsTempalteRespository;
import freemarker.cache.StringTemplateLoader;
import freemarker.template.Configuration;
import freemarker.template.Template;
import org.apache.commons.io.IOUtils;
import org.apache.commons.lang3.StringUtils;
import org.bson.types.ObjectId;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.*;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.gridfs.GridFsResource;
import org.springframework.data.mongodb.gridfs.GridFsTemplate;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.ui.freemarker.FreeMarkerTemplateUtils;
import org.springframework.web.client.RestTemplate;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@Service
public class PageService {

    @Autowired
    private CmsPageRespository cmsPageRespository;

    @Autowired
    private CmsConfigRepository cmsConfigRepository;

    @Autowired
    private CmsTempalteRespository cmsTempalteRespository;


    @Autowired
    private GridFsTemplate gridFsTemplate;

    @Autowired
    private GridFSBucket gridFSBucket;

    @Autowired
    private RabbitTemplate rabbitTemplate;

    //页面列表分页查询
    public QueryResponseResult findList(int page, int size, QueryPageRequest queryPageRequest){
        if (queryPageRequest==null){
            queryPageRequest=new QueryPageRequest();
        }
        ExampleMatcher matching = ExampleMatcher.matching();
        ExampleMatcher exampleMatcher = matching.withMatcher("pageAliase", ExampleMatcher.GenericPropertyMatchers.contains());
        CmsPage cmsPage = new CmsPage();
        //站点id
        if (StringUtils.isNotBlank(queryPageRequest.getSiteId())){
            cmsPage.setSiteId(queryPageRequest.getSiteId());
        }
        //模版id
        if (StringUtils.isNotBlank(queryPageRequest.getTemplateId())){
            cmsPage.setTemplateId(queryPageRequest.getTemplateId());
        }
        //页面别名
        if (StringUtils.isNotBlank(queryPageRequest.getPageAliase())){
            cmsPage.setPageAliase(queryPageRequest.getPageAliase());
        }
        if (page<=0){
            page=1;
        }
        page=page-1;
        Pageable pageable = new PageRequest(page, size);
        Example<CmsPage> example = Example.of(cmsPage, exampleMatcher);
        Page<CmsPage> cmsPages= (Page<CmsPage>) cmsPageRespository.findAll(example,pageable);
        QueryResult<CmsPage> queryResult = new QueryResult<>();
        queryResult.setList(cmsPages.getContent());
        queryResult.setTotal(cmsPages.getTotalElements());
        return new QueryResponseResult(CommonCode.SUCCESS,queryResult);
    }


    //先判断创建的页面是否已经存在，如果已经存在不执行新增操作，直接返回失败状态码
    public CmsPageResult add(CmsPage cmsPage) {
        if (cmsPage==null){
            cmsPage=new CmsPage();
        }

        CmsPage cmsPage1 = cmsPageRespository.findByPageNameAndSiteIdAndPageWebPath(cmsPage.getPageName(),
                cmsPage.getSiteId(), cmsPage.getPageWebPath());

        if(cmsPage1 !=null){
            //校验页面是否存在，已存在则抛出异常
            ExceptionCast.cast(CmsCode.CMS_ADDPAGE_EXISTS);
        }
        if (cmsPage1==null){
            cmsPage.setPageId(null); //有SpringData自动生成
            cmsPageRespository.save(cmsPage);
            return new CmsPageResult(CommonCode.SUCCESS,cmsPage);
        }


        return new CmsPageResult(CommonCode.FAIL,null);
    }

    public CmsPage findById(String id) {
        Optional<CmsPage> optional = cmsPageRespository.findById(id);
        if (optional.isPresent()){
            return optional.get();
        }
        return null;
    }

    public CmsPageResult edit(String id, CmsPage cmsPage) {
        CmsPage one = this.findById(id);
        one.setTemplateId(cmsPage.getTemplateId()); //更新所属站点
        one.setSiteId(cmsPage.getSiteId()); //更新页面别名
        one.setPageAliase(cmsPage.getPageAliase()); //更新页面名称
        one.setPageName(cmsPage.getPageName()); //更新访问路径
        one.setPageWebPath(cmsPage.getPageWebPath()); //更新物理路径
        one.setPagePhysicalPath(cmsPage.getPagePhysicalPath()); //执行更新
        one.setDataUrl(cmsPage.getDataUrl());//更新dataUrl
        CmsPage save = cmsPageRespository.save(one);
        if (save != null) {
            CmsPageResult cmsPageResult = new CmsPageResult(CommonCode.SUCCESS, save);  // 返回成功
            return cmsPageResult;
        }
        return new CmsPageResult(CommonCode.FAIL,null);
    }


    @Autowired
    private RestTemplate restTemplate;

    //页面静态化
    public String getPageHtml(String pageId){
        //获取页面模型数据
       Map model= this.getModelByPageId(pageId);
        if(model == null){
            //数据模型获取不到
            ExceptionCast.cast(CmsCode.CMS_GENERATEHTML_TEMPLATEISNULL);
        }
        //获取页面的模板信息
        String template = getTemplateByPageId(pageId);
        if(StringUtils.isEmpty(template)){
            ExceptionCast.cast(CmsCode.CMS_GENERATEHTML_TEMPLATEISNULL);
        }
        //执行静态化
        String html=generateHtml(template,model);
        if(StringUtils.isEmpty(html)){
            ExceptionCast.cast(CmsCode.CMS_GENERATEHTML_TEMPLATEISNULL);
        }
        return html;
    }


    private String generateHtml(String template, Map model) {
        try {
            //生成配置类
            Configuration configuration = new Configuration(Configuration.getVersion());
            //模版加载器
            StringTemplateLoader stringTemplateLoader = new StringTemplateLoader();
            stringTemplateLoader.putTemplate("template",template);
            //配置模版加载器
            configuration.setTemplateLoader(stringTemplateLoader);
            //获取模版
            Template template1 = configuration.getTemplate("template");
            String html = FreeMarkerTemplateUtils.processTemplateIntoString(template1, model);
            return html;
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }

    private String getTemplateByPageId(String pageId) {
        //查询页面信息
        CmsPage cmsPage=this.findById(pageId);
        if (cmsPage==null){
            //页面不存在
            ExceptionCast.cast(CmsCode.CMS_ADDPAGE_EXISTS);
        }
        //页面模板
        String templateId = cmsPage.getTemplateId();
        if(StringUtils.isEmpty(templateId)) {
            //页面不存在
            ExceptionCast.cast(CmsCode.CMS_GENERATEHTML_TEMPLATEISNULL);
        }
        Optional<CmsTemplate> optional = cmsTempalteRespository.findById(templateId);
        if (optional.isPresent()){
            CmsTemplate cmsTemplate = optional.get();
            //模板文件id
            String templateFileId = cmsTemplate.getTemplateFileId();
            //使用GridFs取出相关模版数据
            GridFSFile gridFSFile = gridFsTemplate.findOne(Query.query(Criteria.where("_id").is(templateFileId)));
            //打开下载流对象
            GridFSDownloadStream gridFSDownloadStream = gridFSBucket.openDownloadStream(gridFSFile.getObjectId());
            //创建GridFsRource
            GridFsResource gridFsResource = new GridFsResource(gridFSFile, gridFSDownloadStream);
            try {
                String content = IOUtils.toString(gridFsResource.getInputStream(), "utf-8");
                return content;
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return null;
    }

    private Map getModelByPageId(String pageId) {
        //查询页面信息
        CmsPage cmsPage=this.findById(pageId);
        if (cmsPage==null){
            //页面不存在
            ExceptionCast.cast(CmsCode.CMS_ADDPAGE_EXISTS);
        }
        //取出页面的dataUrl
        //取出页面的dataUrl
        String dataUrl = cmsPage.getDataUrl();
        if(StringUtils.isEmpty(dataUrl)){
            //页面dataUrl为空
            ExceptionCast.cast(CmsCode.CMS_GENERATEHTML_TEMPLATEISNULL);
        }
        //通过restTemplate请求dataUrl获取数据
        ResponseEntity<Map> forEntity = restTemplate.getForEntity(dataUrl, Map.class);
        Map body = forEntity.getBody();

        return body;
    }

    public ResponseResult postPage(String pageId) {
        String content = this.getPageHtml(pageId);
        if (StringUtils.isEmpty(content)){
            ExceptionCast.cast(CmsCode.CMS_GENERATEHTML_TEMPLATEISNULL);
        }
        //保存静态页面
       CmsPage cmsPage= saveHtml(pageId,content);
       //保存完页面发送消息
       sendPostPage(pageId,cmsPage.getSiteId());
       return new ResponseResult(CommonCode.SUCCESS);
    }

    //发送消息到rabbitMq
    private void sendPostPage(String pageId,String siteId) {
        Map<String,String> msgMap=new HashMap<>();
        msgMap.put("pageId",pageId);
        String msg = JSON.toJSONString(msgMap);
        //获取站点id为routingKey
        rabbitTemplate.convertAndSend(RabbitmqConfig.EX_ROUTING_CMS_POSTPAGE,siteId,msg);
    }

    private CmsPage saveHtml(String pageId, String content) {
        Optional<CmsPage> optional = cmsPageRespository.findById(pageId);
        if(!optional.isPresent()){
            ExceptionCast.cast(CmsCode.CMS_GENERATEHTML_TEMPLATEISNULL);
        }
        CmsPage cmsPage = optional.get();
        String htmlFileId = cmsPage.getHtmlFileId();
        //如果有相关静态页面，先删除
        if (StringUtils.isNotBlank(htmlFileId)){
            gridFsTemplate.delete(Query.query(Criteria.where("_id").is(htmlFileId)));
        }
        //保存html文件到GridFs
        InputStream inputStream = IOUtils.toInputStream(content);
        ObjectId objectId = gridFsTemplate.store(inputStream, cmsPage.getPageName());
        //文件id
        String fileId = objectId.toString();
        //将文件id到cmsPage中
        cmsPage.setHtmlFileId(fileId);
        cmsPageRespository.save(cmsPage);
        return cmsPage;
    }
}

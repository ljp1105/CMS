package com.xuecheng.manage_cms_client.service;


import com.mongodb.client.gridfs.GridFSBucket;
import com.mongodb.client.gridfs.GridFSDownloadStream;
import com.mongodb.client.gridfs.model.GridFSFile;
import com.xuecheng.framework.domain.cms.CmsPage;
import com.xuecheng.framework.domain.cms.CmsSite;
import com.xuecheng.framework.exception.CmsCode;
import com.xuecheng.framework.exception.ExceptionCast;
import com.xuecheng.manage_cms_client.dao.CmsPageRepository;
import com.xuecheng.manage_cms_client.dao.CmsSiteRepository;
import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.gridfs.GridFsResource;
import org.springframework.data.mongodb.gridfs.GridFsTemplate;
import org.springframework.stereotype.Service;

import java.io.*;
import java.util.Optional;


@Service
public class PageService {

    @Autowired
    private CmsPageRepository cmsPageRespository;


    @Autowired
    private CmsSiteRepository cmsSiteRepository;

    @Autowired
    private GridFsTemplate gridFsTemplate;


    @Autowired
    private GridFSBucket gridFSBucket;

    //将页面html保存到页面物理路径
    public void sagePageToServerPath(String pageId){
        //根据pageId获取站点相关信息和页面静态页面相关信息
        Optional<CmsPage> optional = cmsPageRespository.findById(pageId);
        if (!optional.isPresent()){
            ExceptionCast.cast(CmsCode.CMS_GENERATEHTML_TEMPLATEISNULL);
        }
        //取出页面物理路径
        CmsPage cmsPage = optional.get();
        //取出所属站点及相应的物理路径
        CmsSite cmsSite = this.getCmsSiteById(cmsPage.getSiteId());
        String pagePath=cmsSite.getSitePhysicalPath()+cmsPage.getPagePhysicalPath()+cmsPage.getPageName();
        String htmlFileId = cmsPage.getHtmlFileId();
        //根据文件id获取相关id文件
        InputStream inputStream=getFileById(htmlFileId);

        //將文件写入到本地
        FileOutputStream fileOutputStream=null;
        try {
            fileOutputStream = new FileOutputStream(new File(pagePath));
            IOUtils.copy(inputStream,fileOutputStream);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            try {
                inputStream.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
            try {
                fileOutputStream.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private InputStream getFileById(String htmlFileId) {
        GridFSFile gridFSFile = gridFsTemplate.findOne(Query.query(Criteria.where("_id").is(htmlFileId)));
        GridFSDownloadStream gridFSDownloadStream = gridFSBucket.openDownloadStream(gridFSFile.getObjectId());
        GridFsResource gridFsResource = new GridFsResource(gridFSFile, gridFSDownloadStream);
        try {
            return gridFsResource.getInputStream();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    CmsSite getCmsSiteById(String siteId){
       Optional<CmsSite> optional = cmsSiteRepository.findById(siteId);
       if (!optional.isPresent()){
          return null;
       }
       return optional.get();
   }
}

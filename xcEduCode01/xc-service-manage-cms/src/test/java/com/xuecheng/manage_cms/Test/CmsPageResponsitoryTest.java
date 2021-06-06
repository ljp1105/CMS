//package com.xuecheng.manage_cms.Test;
//
//import com.xuecheng.framework.domain.cms.CmsPageParam;
//import com.xuecheng.manage_cms.dao.CmsPageRespository;
//import com.xuecheng.framework.domain.cms.CmsPage;
//import org.junit.Test;
//import org.junit.runner.RunWith;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.data.domain.Example;
//import org.springframework.data.domain.ExampleMatcher;
//import org.springframework.data.domain.Page;
//import org.springframework.data.domain.PageRequest;
//import org.springframework.test.context.junit4.SpringRunner;
//
//import java.lang.reflect.Array;
//import java.util.ArrayList;
//import java.util.Date;
//import java.util.List;
//import java.util.Optional;
//
//@SpringBootTest
//@RunWith(SpringRunner.class)
//public class CmsPageResponsitoryTest {
//
//
//    @Autowired
//   public CmsPageRespository cmsPageRespository;
//
//
//
//
//    //查詢所有相關頁面信息
//    @Test
//    public void test(){
//        List<CmsPage> list = cmsPageRespository.findAll();
//        System.out.println("打印一下子"+list.size());
//    }
//
//
//    //添加
//    @Test
//    public void test01(){
//        PageRequest pageRequest = PageRequest.of(0, 10);
//        Page<CmsPage> pageList = cmsPageRespository.findAll(pageRequest);
//        System.out.println(pageList.getContent());
//    }
//
//    @Test
//    public void testInsert(){
//        CmsPage cmsPage = new CmsPage();
//        cmsPage.setSiteId("s01");
//        cmsPage.setPageName("測試頁面");
//        cmsPage.setTemplateId("t01");
//        ArrayList<CmsPageParam> list = new ArrayList<>();
//        CmsPageParam cmsPageParam = new CmsPageParam();
//        cmsPageParam.setPageParamName("parm1");
//        cmsPageParam.setPageParamValue("value1");
//        list.add(cmsPageParam);
//        cmsPage.setPageParams(list);
//        cmsPageRespository.save(cmsPage);
//        System.out.println(cmsPage);
//    }
//
//    @Test
//    public void testDelete(){
//        cmsPageRespository.deleteById("5fc3b98a39a5b52bacf731bc");
//    }
//
//    @Test
//    public void testUpdate(){
//        Optional<CmsPage> optional = cmsPageRespository.findById("5fc3baba39a5b53ad4cd4dd5");
//        if (optional.isPresent()){
//            CmsPage cmsPage = optional.get();
//            System.out.println(cmsPage);
//            cmsPage.setPageName("測試頁面001");
//            cmsPageRespository.save(cmsPage);
//            System.out.println("修改完成");
//        }
//    }
//
//
//    /**
//     * 需求
//     * 1.站点id:精确匹配
//     * 2.模版id:精确匹配
//     * 3.页面别名:模糊匹配
//     */
//
//    @Test
//    public void testFindAll(){
//        ExampleMatcher exampleMatcher = ExampleMatcher.matching();
//        ExampleMatcher withMatcher = exampleMatcher.withMatcher("pageAliase", ExampleMatcher.GenericPropertyMatchers.contains());
//        //页面别名查询模式搜索需要自定义
//        CmsPage cmsPage = new CmsPage();
////        //设置站点id
////        cmsPage.setSiteId("5a751fab6abb5044e0d19ea1");
////        //设置模版id
////        cmsPage.setTemplateId("5a962c16b00ffc514038fafd");
//        //设置页面别名
//        cmsPage.setPageAliase("课程");
//        Example<CmsPage> example = Example.of(cmsPage, withMatcher);
//
//        PageRequest pageRequest = new PageRequest(0, 10);
//        Page<CmsPage> all = cmsPageRespository.findAll(example, pageRequest);
//        System.out.println(all.getTotalElements());
//
//    }
//
//
//
//}

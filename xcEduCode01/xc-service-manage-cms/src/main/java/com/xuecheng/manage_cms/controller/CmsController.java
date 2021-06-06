package com.xuecheng.manage_cms.controller;

import com.xuecheng.api.cms.CmsPageControllerApi;
import com.xuecheng.framework.domain.cms.CmsPage;
import com.xuecheng.framework.domain.cms.request.QueryPageRequest;
import com.xuecheng.framework.domain.cms.response.CmsPageResult;
import com.xuecheng.framework.model.response.CommonCode;
import com.xuecheng.framework.model.response.QueryResponseResult;
import com.xuecheng.framework.model.response.QueryResult;
import com.xuecheng.framework.model.response.ResponseResult;
import com.xuecheng.manage_cms.service.PageService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.ServletInputStream;
import javax.servlet.http.HttpServletRequest;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/cms/page")
public class CmsController implements CmsPageControllerApi {


    @Autowired
    private PageService pageService;

    @GetMapping("/list/{page}/{size}")
    @Override
    public QueryResponseResult findList(
            @PathVariable(value = "page") int page,
            @PathVariable(value = "size") int size,
            QueryPageRequest queryPageRequest
    ) {
        QueryResponseResult queryResponseResult = pageService.findList(page, size, queryPageRequest);
        return queryResponseResult;
    }

    @Override
    @PostMapping("/add")
    public CmsPageResult add(@RequestBody CmsPage cmsPage) {
        return pageService.add(cmsPage);
    }

    @GetMapping("/get/{id}")
    @Override
    public CmsPage findById(@PathVariable("id") String id) {
        return pageService.findById(id);
    }

    @PutMapping("/edit/{id}")
    @Override
    public CmsPageResult edit(@PathVariable("id") String id,@RequestBody CmsPage cmsPage) {
        return pageService.edit(id,cmsPage);
    }

    @PostMapping("/postPage/{pageId}")
    public ResponseResult post(@PathVariable(value = "pageId") String pageId) {
        return pageService.postPage(pageId);
    }





}

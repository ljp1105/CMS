package com.xuecheng.manage_cms.controller;


import com.xuecheng.manage_cms.ManageCmsApplication;
import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import junit.framework.JUnit4TestAdapter;
import org.apache.commons.io.IOUtils;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.ui.freemarker.FreeMarkerTemplateUtils;
import org.springframework.web.client.RestTemplate;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;

@SpringBootTest
@RunWith(SpringRunner.class)

public class CreateIndexBanner {
    @Autowired
    RestTemplate restTemplate;

    @Test
    public void createHtml() throws IOException, TemplateException {
        //创建配置类
        Configuration configuration = new Configuration(Configuration.getVersion());
        //设置模版路径
//        String classPath = this.getClass().getResource("/").getPath();
        configuration.setDirectoryForTemplateLoading(new File("D:\\"));
        //设置字符集
        configuration.setDefaultEncoding("utf-8");
        //加载模版
        Template template = configuration.getTemplate("index_banner.ftl");
        //数据模版
        Map<Object, Object> map = new HashMap<>();
        String dataUrl = "http://localhost:31001/cms/config/getmodel/5a791725dd573c3574ee333f";
        ResponseEntity<Map> forEntity = restTemplate.getForEntity(dataUrl, Map.class);
        Map<String,Object> body = forEntity.getBody(); map.putAll(body);
        //执行静态化将静态文件写入到本地
        String content = FreeMarkerTemplateUtils.processTemplateIntoString(template, map);
        InputStream inputStream = IOUtils.toInputStream(content);
        //输出文件到本地
        FileOutputStream fileOutputStream = new FileOutputStream("D:\\wps\\test.html");
        IOUtils.copy(inputStream,fileOutputStream);
        inputStream.close();
        fileOutputStream.close();
    }

}

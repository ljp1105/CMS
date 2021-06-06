package com.xuecheng.manage_cms.Test;


import org.bson.types.ObjectId;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.mongodb.gridfs.GridFsTemplate;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

@SpringBootTest
@RunWith(SpringRunner.class)
public class GridFsTempalteTest {

    @Autowired
    private GridFsTemplate gridFsTempalte;

    @Test
    public void testGridFs() throws FileNotFoundException {
        //通过IO流读取要存储的文件
        File file = new File("D:\\wps\\index_banner.ftl");
        //定义输出流
        FileInputStream fileInputStream = new FileInputStream(file);
        //向GriFs存储文件
        ObjectId objectId = gridFsTempalte.store(fileInputStream, "轮播图模版文件");
        String fileId = objectId.toString();
        System.out.println(fileId);


    }

}

package com.xuecheng.manage_cms.Test;


import com.mongodb.client.gridfs.GridFSBucket;
import com.mongodb.client.gridfs.GridFSDownloadStream;
import com.mongodb.client.gridfs.model.GridFSFile;
import org.apache.commons.io.IOUtils;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.gridfs.GridFsResource;
import org.springframework.data.mongodb.gridfs.GridFsTemplate;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.IOException;

@SpringBootTest
@RunWith(SpringRunner.class)
public class GridFsTest {

    @Autowired
    GridFsTemplate gridFsTemplate;

    @Autowired
    GridFSBucket gridFSBucket;

    @Test
    public void queryFile() throws IOException {
        String filedId="604b951439a5b557d03372d5";
        //根据id查询文件
        GridFSFile gridFSFile = gridFsTemplate.findOne(Query.query(Criteria.where("_id").is(filedId)));
        //打开下载流对象
        GridFSDownloadStream gridFSDownloadStream = gridFSBucket.openDownloadStream(gridFSFile.getObjectId());
        //创建gridFsResource,用于获取流对象
        GridFsResource gridFsResource = new GridFsResource(gridFSFile, gridFSDownloadStream);
        //获取流中的数据
        String s = IOUtils.toString(gridFsResource.getInputStream(), "UTF-8");
        System.out.println("===="+s);
    }

    @Test
    public void testDeleFile(){
        //根据文件id删除fs.files和fs.chunks心中的记录
        gridFsTemplate.delete(Query.query(Criteria.where("id").is("604b951439a5b557d03372d5")));
    }
}

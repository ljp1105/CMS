package com.xuecheng.manage_course.dao;


import com.xuecheng.framework.domain.course.Teachplan;
import com.xuecheng.framework.domain.course.ext.TeachplanNode;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface TeachplanMapper {
    public TeachplanNode selectList(@Param(value = "courseId") String courseId);
}

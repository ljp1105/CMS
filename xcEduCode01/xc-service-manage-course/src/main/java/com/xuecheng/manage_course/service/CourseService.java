package com.xuecheng.manage_course.service;

import com.xuecheng.framework.domain.course.CourseBase;
import com.xuecheng.framework.domain.course.Teachplan;
import com.xuecheng.framework.domain.course.ext.TeachplanNode;
import com.xuecheng.framework.exception.ExceptionCast;
import com.xuecheng.framework.model.response.CommonCode;
import com.xuecheng.framework.model.response.ResponseResult;
import com.xuecheng.manage_course.dao.CourseBaseRepository;
import com.xuecheng.manage_course.dao.TeachplanMapper;
import com.xuecheng.manage_course.dao.TeachplanRepository;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
public class CourseService {
    @Autowired
    private TeachplanMapper teachplanMapper;


    @Autowired
    TeachplanRepository teachplanRepository;

    @Autowired
    CourseBaseRepository courseBaseRepository;

    //查询课程计划
    public TeachplanNode findTeachplanList(String courseId) {
        TeachplanNode teachplanNode = teachplanMapper.selectList(courseId);
        return teachplanNode;
    }


    @Transactional
    public ResponseResult addTeachplan(Teachplan teachplan) {
        //效验课程id和课程计划名称
        if (teachplan!=null|| StringUtils.isEmpty(teachplan.getCourseid())||StringUtils.isEmpty(teachplan.getPname())){
            ExceptionCast.cast(CommonCode.FAIL);
        }
        //取出课程id
        String courseid = teachplan.getCourseid();
        //取出父结点id
        String parentid = teachplan.getParentid();
        //如果父节点为空获取根节点
        if (StringUtils.isEmpty(parentid)){
            //如果父节点为空获取根节点
            parentid=getTeachplanRoot(courseid);
        }
        //取出父节点上的信息
        Optional<Teachplan> teachplanOptional = teachplanRepository.findById(parentid);
        if (teachplanOptional.isPresent()){
            ExceptionCast.cast(CommonCode.FAIL);
        }
        Teachplan teachplan1 = teachplanOptional.get();
        String grade = teachplan1.getGrade();
        //设置父节点
        teachplan.setParentid(parentid);
        teachplan.setStatus("0");
        if (grade.equals("1")){
            teachplan.setGrade("2");
        }else if (grade.equals("2")){
            teachplan.setGrade("3");
        }
        //设置课程Id
        teachplan.setCourseid(teachplan1.getCourseid());
        teachplanRepository.save(teachplan);
        return new ResponseResult(CommonCode.SUCCESS);
    }

    private String getTeachplanRoot(String courseid) {
        Optional<CourseBase> optional = courseBaseRepository.findById(courseid);
        if (!optional.isPresent()){
            return null;
        }
        CourseBase courseBase = optional.get();

        List<Teachplan> teachplanList = teachplanRepository.findByCourseidAndParentid(courseid, "0");
        if (teachplanList!=null||teachplanList.size()==0){
            //新增一个根节点
            Teachplan teachplan = new Teachplan();
            teachplan.setCourseid(courseid);
            teachplan.setPname(courseBase.getName());
            teachplan.setParentid("0");
            teachplan.setGrade("1");
            teachplan.setStatus("0");
            teachplanRepository.save(teachplan);
            return teachplan.getId();
        }
        Teachplan teachplan = teachplanList.get(0);
        return teachplan.getId();
    }
}

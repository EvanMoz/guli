package com.tencent.oa.eduservice.service.impl;

import com.tencent.oa.eduservice.entity.EduCourse;
import com.tencent.oa.eduservice.entity.EduCourseDescription;
import com.tencent.oa.eduservice.entity.vo.CourseInfoVo;
import com.tencent.oa.eduservice.mapper.EduCourseMapper;
import com.tencent.oa.eduservice.service.EduCourseDescriptionService;
import com.tencent.oa.eduservice.service.EduCourseService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.tencent.oa.servicebase.exception.GuliException;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 课程 服务实现类
 * </p>
 *
 * @author EvanMoz
 * @since 2021-01-11
 */
@Service
public class EduCourseServiceImpl extends ServiceImpl<EduCourseMapper, EduCourse> implements EduCourseService {

    @Autowired
    private EduCourseDescriptionService eduCourseDescriptionService;

    @Override
    public String saveCourseInfo(CourseInfoVo courseInfoVo) {
        //向课程表添加课程基本信息
        EduCourse eduCourse = new EduCourse();
        BeanUtils.copyProperties(courseInfoVo,eduCourse);
        int insert = baseMapper.insert(eduCourse);
        if (insert ==0){
            throw new GuliException(20001,"添加课程信息失败");
        }

        String cid = eduCourse.getId();

        //向课程简介表添加课程简介
        EduCourseDescription eduCourseDescription = new EduCourseDescription();
        eduCourseDescription.setDescription(courseInfoVo.getDescription());
        eduCourseDescription.setId(cid);
        eduCourseDescriptionService.save(eduCourseDescription);
        return cid;
    }
}

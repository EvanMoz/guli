package com.tencent.oa.eduservice.service;

import com.tencent.oa.eduservice.entity.EduCourse;
import com.baomidou.mybatisplus.extension.service.IService;
import com.tencent.oa.eduservice.entity.vo.CourseInfoVo;

/**
 * <p>
 * 课程 服务类
 * </p>
 *
 * @author EvanMoz
 * @since 2021-01-11
 */
public interface EduCourseService extends IService<EduCourse> {

    String saveCourseInfo(CourseInfoVo courseInfoVo);

    CourseInfoVo getCourseInfo(String courseId);

    void updateCourseInfo(CourseInfoVo courseInfoVo);
}

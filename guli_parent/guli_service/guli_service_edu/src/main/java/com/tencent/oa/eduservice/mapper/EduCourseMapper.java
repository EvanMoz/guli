package com.tencent.oa.eduservice.mapper;

import com.tencent.oa.eduservice.entity.EduCourse;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.tencent.oa.eduservice.entity.vo.CoursePublishVo;

/**
 * <p>
 * 课程 Mapper 接口
 * </p>
 *
 * @author EvanMoz
 * @since 2021-01-11
 */
public interface EduCourseMapper extends BaseMapper<EduCourse> {
    public CoursePublishVo getPublishCourseInfo(String courseId);
}

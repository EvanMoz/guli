package com.tencent.oa.eduservice.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.tencent.oa.commonutils.JsonResult;
import com.tencent.oa.eduservice.entity.EduCourse;
import com.tencent.oa.eduservice.entity.vo.CourseInfoVo;
import com.tencent.oa.eduservice.entity.vo.CoursePublishVo;
import com.tencent.oa.eduservice.entity.vo.CourseQuery;
import com.tencent.oa.eduservice.service.EduCourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * <p>
 * 课程 前端控制器
 * </p>
 *
 * @author EvanMoz
 * @since 2021-01-11
 */
@RestController
@RequestMapping("/eduservice/course")
@CrossOrigin
public class EduCourseController {

    @Autowired
    private EduCourseService eduCourseService;

    @PostMapping("getCoursePageList/{current}/{limit}")
    public JsonResult getCoursePageList(@PathVariable long current,
                                        @PathVariable long limit,
                                        @RequestBody(required = false) CourseQuery courseQuery){
        Page<EduCourse> pageCourse = new Page<>(current,limit);
        QueryWrapper<EduCourse> queryWrapper = new QueryWrapper<>();
        String title = courseQuery.getTitle();
        String status = courseQuery.getStatus();


        if(!StringUtils.isEmpty(title)){
            queryWrapper.like("title",title);
        }
        if (!StringUtils.isEmpty(status)){
            queryWrapper.eq("status",status);
        }


        eduCourseService.page(pageCourse,queryWrapper);
        List<EduCourse> records = pageCourse.getRecords();
        long total = pageCourse.getTotal();
        return JsonResult.success().data("total",total).data("items",records);

    }

    @PostMapping("addCourseInfo")
    public JsonResult addCourseInfo(@RequestBody CourseInfoVo courseInfoVo){
        String courseId = eduCourseService.saveCourseInfo(courseInfoVo);


        return JsonResult.success().data("courseId",courseId);
    }

    @GetMapping("getCourseInfo/{courseId}")
    public JsonResult getCourseInfo(@PathVariable String courseId){
        CourseInfoVo courseInfoVo = eduCourseService.getCourseInfo(courseId);
        return JsonResult.success().data("courseInfoVo",courseInfoVo);
    }

    @PostMapping("updateCourseInfo")
    public JsonResult updateCourseInfo(@RequestBody CourseInfoVo courseInfoVo){
        eduCourseService.updateCourseInfo(courseInfoVo);
        return JsonResult.success();
    }

    @GetMapping("getPublishCourseInfo/{courseId}")
    public JsonResult getPublishCourseInfo(@PathVariable String courseId){
        CoursePublishVo coursePublishVo = eduCourseService.getPublishCourseInfo(courseId);
        return JsonResult.success().data("publishCourseInfo",coursePublishVo);
    }

    @PostMapping("publishCourse/{courseId}")
    public JsonResult publishCourse(@PathVariable String courseId){
        EduCourse eduCourse = new EduCourse();
        eduCourse.setId(courseId);
        eduCourse.setStatus("Normal");
        eduCourseService.updateById(eduCourse);
        return JsonResult.success();
    }


    @DeleteMapping("delete/{courseId}")
    public JsonResult deleteCourseById(@PathVariable String courseId){
        eduCourseService.deleteCourseById(courseId);
        return JsonResult.success();
    }
}


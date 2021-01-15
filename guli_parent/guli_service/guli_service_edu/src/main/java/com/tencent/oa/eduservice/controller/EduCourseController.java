package com.tencent.oa.eduservice.controller;


import com.tencent.oa.commonutils.JsonResult;
import com.tencent.oa.eduservice.entity.vo.CourseInfoVo;
import com.tencent.oa.eduservice.service.EduCourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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
}


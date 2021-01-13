package com.tencent.oa.eduservice.controller;


import com.tencent.oa.commonutils.JsonResult;
import com.tencent.oa.eduservice.entity.subject.OneSubject;
import com.tencent.oa.eduservice.service.EduSubjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import org.springframework.web.multipart.MultipartFile;

import java.util.List;


/**
 * <p>
 * 课程科目 前端控制器
 * </p>
 *
 * @author EvanMoz
 * @since 2021-01-08
 */
@RestController
@RequestMapping("/eduservice/subject")
@CrossOrigin
public class EduSubjectController {

    @Autowired
    private EduSubjectService subjectService;

    //获取上传文件,读取文件内容
    @PostMapping("addSubject")
    public JsonResult addSubject(MultipartFile file){
        subjectService.saveSubject(file,subjectService);
        return JsonResult.success();
    }

    //课程分类列表(树形结构)
    @GetMapping("getAllSubject")
    public JsonResult getAllSubject(){
        List<OneSubject> list =subjectService.getAllOneTwoSubject();
        return JsonResult.success().data("list",list);
    }


}


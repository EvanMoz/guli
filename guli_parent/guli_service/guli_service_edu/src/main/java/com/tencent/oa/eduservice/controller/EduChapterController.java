package com.tencent.oa.eduservice.controller;


import com.tencent.oa.commonutils.JsonResult;
import com.tencent.oa.eduservice.chapter.ChapterVo;
import com.tencent.oa.eduservice.service.EduChapterService;
import org.springframework.beans.factory.annotation.Autowired;
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
@RequestMapping("/eduservice/chapter")
@CrossOrigin
public class EduChapterController {

    @Autowired
    private EduChapterService eduChapterService;

    @GetMapping("getChapterVideo/{courseId}")
    public JsonResult getChapterVideo(@PathVariable String courseId) {
        List<ChapterVo> list = eduChapterService.getChapterVideoByCourseId(courseId);

        return JsonResult.success().data("allChapterVideo",list);
    }
}


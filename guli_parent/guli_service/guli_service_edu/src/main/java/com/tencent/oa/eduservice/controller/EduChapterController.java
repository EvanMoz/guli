package com.tencent.oa.eduservice.controller;


import com.tencent.oa.commonutils.JsonResult;
import com.tencent.oa.eduservice.chapter.ChapterVo;
import com.tencent.oa.eduservice.entity.EduChapter;
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

    @PostMapping("addChapter")
    public JsonResult addChapter(@RequestBody EduChapter eduChapter){
        eduChapterService.save(eduChapter);
        return JsonResult.success();
    }

    @GetMapping("getChapterInfo/{chapterId}")
    public JsonResult getChapterInfo(@PathVariable String chapterId){
        EduChapter eduChapter = eduChapterService.getById(chapterId);
        return JsonResult.success().data("chapterInfo",eduChapter);
    }

    @PostMapping("updateChapter")
    public JsonResult updateChapter(@RequestBody EduChapter eduChapter){
        eduChapterService.updateById(eduChapter);
        return JsonResult.success();
    }

    @DeleteMapping("deleteChapter/{chapterId}")
    public JsonResult deleteChapter(@PathVariable String chapterId){
        boolean flag = eduChapterService.deleteChapter(chapterId);
        if (flag) {
            return JsonResult.success();
        }else {
            return JsonResult.failed();
        }
    }
}


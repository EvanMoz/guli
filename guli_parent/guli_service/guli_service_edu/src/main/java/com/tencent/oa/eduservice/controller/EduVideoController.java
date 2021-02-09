package com.tencent.oa.eduservice.controller;


import com.tencent.oa.commonutils.JsonResult;
import com.tencent.oa.eduservice.client.VodClient;
import com.tencent.oa.eduservice.entity.EduVideo;
import com.tencent.oa.eduservice.service.EduVideoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

/**
 * <p>
 * 课程视频 前端控制器
 * </p>
 *
 * @author EvanMoz
 * @since 2021-01-11
 */
@RestController
@RequestMapping("/eduservice/video")
@CrossOrigin
public class EduVideoController {

    @Autowired
    private EduVideoService eduVideoService;

    @Autowired
    private VodClient vodClient;

    @PostMapping("addVideo")
    public JsonResult addVideo(@RequestBody EduVideo eduVideo){
        eduVideoService.save(eduVideo);
        return JsonResult.success();
    }

    //删除小节的时候同时把视频删除
    @DeleteMapping("delete/{videoId}")
    public JsonResult deleteVideo(@PathVariable String videoId){
        //根据小节Id得到视频Id
        EduVideo eduVideo = eduVideoService.getById(videoId);
        String videoSourceId = eduVideo.getVideoSourceId();
        if (!StringUtils.isEmpty(videoSourceId)){
            vodClient.deleteVideoById(videoSourceId);
        }
        eduVideoService.removeById(videoId);
        return JsonResult.success();
    }

    @PostMapping("updateVideo")
    public JsonResult updateVideo(@RequestBody EduVideo eduVideo){
        eduVideoService.updateById(eduVideo);
        return JsonResult.success();
    }

    @GetMapping("getVideoInfo/{videoId}")
    public JsonResult getVideoInfo(@PathVariable String videoId){
        EduVideo eduVideo = eduVideoService.getById(videoId);
        return JsonResult.success().data("videoInfo",eduVideo);
    }
}


package com.tencent.oa.vod.controller;

import com.tencent.oa.commonutils.JsonResult;
import com.tencent.oa.vod.service.VodService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("vod/video")
public class VodController {

    @Autowired
    private VodService vodService;

    @PostMapping("uploadVideo")
    public JsonResult uploadVideo(MultipartFile file){
        String videoId = vodService.uploadVideo(file);
        return JsonResult.success();
    }
}

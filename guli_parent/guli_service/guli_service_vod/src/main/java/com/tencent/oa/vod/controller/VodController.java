package com.tencent.oa.vod.controller;

import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.vod.model.v20170321.DeleteVideoRequest;
import com.tencent.oa.commonutils.JsonResult;
import com.tencent.oa.servicebase.exception.GuliException;
import com.tencent.oa.vod.service.VodService;
import com.tencent.oa.vod.utils.ConstantVodUtils;
import com.tencent.oa.vod.utils.InitVodClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequestMapping("/edu/vod")
@CrossOrigin
public class VodController {

    @Autowired
    private VodService vodService;

    @PostMapping("uploadVideo")
    public JsonResult uploadVideo(MultipartFile file){
        String videoId = vodService.uploadVideo(file);
        return JsonResult.success().data("videoId",videoId);
    }

    @DeleteMapping("deleteVideo/{videoId}")
    public JsonResult deleteVideoById(@PathVariable String videoId){
        try {
            DefaultAcsClient client = InitVodClient.initVodClient(ConstantVodUtils.ACCESS_KEY_ID, ConstantVodUtils.ACCESS_KEY_SECRET);
            DeleteVideoRequest request = new DeleteVideoRequest();
            request.setVideoIds(videoId);
            client.getAcsResponse(request);

            return JsonResult.success();
        }catch (Exception e){
            e.printStackTrace();
            throw new GuliException(20001,"删除视频失败");
        }

    }

    @DeleteMapping("delete-batch")
    public JsonResult deleteBatch(@RequestParam("videoIdList") List<String> videoIdList){
        vodService.removeMultiVideo(videoIdList);
        return JsonResult.success();
    }
}

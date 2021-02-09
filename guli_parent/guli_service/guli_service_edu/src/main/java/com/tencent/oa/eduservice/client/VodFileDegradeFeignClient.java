package com.tencent.oa.eduservice.client;

import com.tencent.oa.commonutils.JsonResult;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;

import java.util.List;

@FeignClient(name = "service-vod",fallback = VodFileDegradeFeignClient.class)
@Component
public class VodFileDegradeFeignClient implements VodClient{

    @Override
    public JsonResult deleteVideoById(String videoId) {
        return JsonResult.failed().message("删除视频出错");
    }

    @Override
    public JsonResult deleteBatch(List<String> videoIdList) {
        return JsonResult.failed().message("删除多个视频出错");
    }
}

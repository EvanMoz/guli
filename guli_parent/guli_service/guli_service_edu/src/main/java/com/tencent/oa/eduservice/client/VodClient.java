package com.tencent.oa.eduservice.client;

import com.tencent.oa.commonutils.JsonResult;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@FeignClient(name = "service-vod")
@Component
public interface VodClient {

    @DeleteMapping("/edu/vod/deleteVideo/{videoId}")
    public JsonResult deleteVideoById(@PathVariable String videoId);

    @DeleteMapping("/edu/vod/delete-batch")
    public JsonResult deleteBatch(@RequestParam("videoIdList") List<String> videoIdList);

}

package com.tencent.oa.oss.controller;

import com.tencent.oa.commonutils.JsonResult;
import com.tencent.oa.oss.service.OssService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;



@RestController
@RequestMapping("/edu/oss")
@CrossOrigin
public class OssController {

    @Autowired
    private OssService ossService;

    @ApiOperation("文件上传")
    @PostMapping("avatar")
    public JsonResult uploadOssFile(MultipartFile file) {
        //获取上传文件 MultipartFile
        //返回上传到oss的路径
        String url = ossService.uploadFileAvatar(file);
        return JsonResult.success().data("url",url);
    }
}

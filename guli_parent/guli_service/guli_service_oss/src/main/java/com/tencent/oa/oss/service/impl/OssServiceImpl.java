package com.tencent.oa.oss.service.impl;





import com.aliyun.oss.OSS;
import com.aliyun.oss.OSSClientBuilder;
import com.tencent.oa.oss.service.OssService;
import com.tencent.oa.oss.utils.ConstantPropertiesUtils;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.ByteArrayInputStream;
import java.io.InputStream;


@Service
public class OssServiceImpl implements OssService {
    @Override
    public String uploadFileAvatar(MultipartFile file) {
        //获取阿里云存储相关常量
        String endPoint = ConstantPropertiesUtils.END_POINT;
        String accessKeyId = ConstantPropertiesUtils.ACCESS_KEY_ID;
        String accessKeySecret = ConstantPropertiesUtils.ACCESS_KEY_SECRET;
        String bucketName = ConstantPropertiesUtils.BUCKET_NAME;


        String uploadUrl = null;
        try {
            //判断oss实例是否存在：如果不存在则创建，如果存在则获取
            OSS ossClient = new OSSClientBuilder().build(endPoint, accessKeyId, accessKeySecret);

            //获取上传文件流
            InputStream inputStream = file.getInputStream();
            String fileName = file.getOriginalFilename();

            String content = "Hello OSS";
            //文件上传至阿里云
            ossClient.putObject(bucketName, fileName, inputStream);

            // 关闭OSSClient。
            ossClient.shutdown();
            uploadUrl = "http://" + bucketName + "." + endPoint + "/" + fileName;
        }
            catch (Exception e){
            e.printStackTrace();

            }


        return uploadUrl;

    }
}

package com.tencent.oa.oss.service.impl;





import com.aliyun.oss.OSS;
import com.aliyun.oss.OSSClientBuilder;
import com.tencent.oa.oss.service.OssService;
import com.tencent.oa.oss.utils.ConstantPropertiesUtils;
import org.joda.time.DateTime;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;


import java.io.InputStream;
import java.util.UUID;


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
            //创建OSS对象
            OSS ossClient = new OSSClientBuilder().build(endPoint, accessKeyId, accessKeySecret);

            //获取上传文件流
            InputStream inputStream = file.getInputStream();
            String fileName = file.getOriginalFilename();

            //文件名称添加随机唯一值
            String uuid = UUID.randomUUID().toString().replaceAll("-","");
            fileName = uuid + fileName;

            String datePath = new DateTime().toString("yyyy/MM/dd");
            fileName = datePath +"/"+fileName;

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

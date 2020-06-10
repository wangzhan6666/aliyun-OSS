package com.sise.wangzhan.service.impl;

import com.aliyun.oss.OSS;
import com.aliyun.oss.OSSBuilder;
import com.aliyun.oss.OSSClient;
import com.aliyun.oss.OSSClientBuilder;
import com.sise.wangzhan.service.OssService;
import com.sise.wangzhan.utils.ConstantPropertiesUtils;
import org.joda.time.DateTime;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.InputStream;
import java.util.UUID;

/**
 * @Classname OssServiceImpl
 * @Description TODO
 * @Date 2020/6/10 9:33
 * @Created by wangzhan
 */
@Service
public class OssServiceImpl implements OssService {

    // 1 上传图片到阿里云oss
    // 详细上传，请看：https://help.aliyun.com/document_detail/84781.html
    @Override
    public String uploadFile(MultipartFile file) {

        // 获取阿里云OSS配置的参数
        String endPoind = ConstantPropertiesUtils.END_POIND;
        String accessKeyId = ConstantPropertiesUtils.ACCESS_KEY_ID;
        String accessKeySecret = ConstantPropertiesUtils.ACCESS_KEY_SECRET;
        String bucketName = ConstantPropertiesUtils.BUCKET_NAME;

        try {
            // 创建OSSClient实例。
            OSS ossClient = new OSSClientBuilder().build(endPoind, accessKeyId, accessKeySecret);

            // 上传文件流。
            InputStream inputStream = file.getInputStream();

            // ***********************第二个参数：生成文件名称 start*******************************
            // 获取文件名称
            String fileName = file.getOriginalFilename();
            // 1 在文件名称里面添加随机唯一的值
            String uuid = UUID.randomUUID().toString().replaceAll("-", "");
            fileName = uuid + fileName;
            // 2 把文件按照日期进行分类
            // 2020/6/1/01.jpg
            // 获取当前日期
            String datePath = new DateTime().toString("yyyy/MM/dd");

            // 拼接   2020/6/1/gaasdga01.jpg
            fileName = datePath + "/" +fileName;

            // ***********************第二个参数：生成文件名称 end*******************************

            ossClient.putObject(bucketName, fileName, inputStream);

            // 把上传之后文件路径返回
            // 需要把上传到阿里云oss路径手动拼接起来
            // https://edu-wangzhan.oss-cn-shenzhen.aliyuncs.com/timg.gif
            String url = "https://" + bucketName + "." + endPoind + "/" + fileName;

            // 关闭OSSClient。
            ossClient.shutdown();

            return url;
        }catch (Exception e) {
            e.printStackTrace();
            return "";
        }
    }
}

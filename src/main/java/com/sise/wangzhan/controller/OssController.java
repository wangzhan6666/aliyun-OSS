package com.sise.wangzhan.controller;

import com.sise.wangzhan.service.OssService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

/**
 * @Classname OssController
 * @Description TODO    swagger测试地址：http://localhost:8090/swagger-ui.html
 * @Date 2020/6/10 9:34
 * @Created by wangzhan
 */
@RestController
@RequestMapping("/wangzhan/oss")
public class OssController {

    @Autowired
    private OssService ossService;

    // 1 上传图片到阿里云oss
    // 返回上传图片的地址
    @ApiOperation("上传图片")
    @PostMapping("upload")
    public String upload(MultipartFile file) {

        String url = ossService.uploadFile(file);

        return url;
    }
}

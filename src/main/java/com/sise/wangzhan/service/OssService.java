package com.sise.wangzhan.service;

import org.springframework.web.multipart.MultipartFile;

/**
 * @Classname OssService
 * @Description TODO
 * @Date 2020/6/10 9:33
 * @Created by wangzhan
 */
public interface OssService {

    // 1 上传图片到阿里云oss
    String uploadFile(MultipartFile file);
}

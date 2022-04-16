package com.cll.graduation.project.service;

import org.springframework.web.multipart.MultipartFile;

public interface CommonService {

    /**
     * 上传单个文件
     *
     * @param file
     * @return
     */
    String uploadFile(MultipartFile file);
}

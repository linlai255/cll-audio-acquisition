package com.cll.graduation.project.controller;

import com.cll.graduation.project.service.CommonService;
import com.cll.graduation.project.vo.Result;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;

@RestController
@RequestMapping("/system/common")
public class CommonController {

    @Resource
    private CommonService commonService;

    /**
     * 上传单个文件
     * @param file
     * @return
     */
    @PostMapping("/uploadFile")
    public Result uploadFile(@RequestBody MultipartFile file) {
        String url = commonService.uploadFile(file);
        return Result.success(url);
    }
}

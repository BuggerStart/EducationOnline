package com.bugstart.oss.controller;

import com.bugstart.oss.service.OssService;
import com.bugstart.utils.CommonResult;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;

/**
 * @author bugstart
 * @create 2023-01-27 22:50
 */
@RestController
@CrossOrigin
@RequestMapping("/eduoss/fileoss")

public class OssController {

    @Resource
    private OssService ossService;

    @PostMapping
    public CommonResult uploadOssFile(MultipartFile file){
        String url=ossService.uploadFileAvatar(file);
        System.out.println("url:"+url);
        if (StringUtils.isEmpty(url)){
            return CommonResult.error();
        }
        return CommonResult.ok().data("url",url);
    }
}

package com.bugstart.oss.service;

import org.springframework.web.multipart.MultipartFile;

/**
 * @author bugstart
 * @create 2023-01-27 22:50
 */
public interface OssService {
    String uploadFileAvatar(MultipartFile file);

}

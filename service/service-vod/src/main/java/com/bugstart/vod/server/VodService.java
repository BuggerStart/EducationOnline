package com.bugstart.vod.server;

import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/**
 * @author bugstart
 * @create 2023-01-31 16:58
 */
public interface VodService {
    String uploadVideoAly(MultipartFile file);

    void removeMoreAlyVideo(List<String> videoIdList);
}

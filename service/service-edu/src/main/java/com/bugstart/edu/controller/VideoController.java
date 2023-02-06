package com.bugstart.edu.controller;


import com.bugstart.edu.client.VodClient;
import com.bugstart.edu.entity.Video;
import com.bugstart.edu.service.VideoService;
import com.bugstart.utils.CommonResult;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * <p>
 * 课程视频 前端控制器
 * </p>
 *
 * @author bugstart
 * @since 2023-01-29
 */
@RestController
@CrossOrigin
@RequestMapping("/edu/video")
public class VideoController {

    @Resource
    private VideoService videoService;

    @Resource
    private VodClient vodClient;

    /**
     * 增加小节
     * @param video
     * @return
     */
    @PostMapping("/addVideo")
    public CommonResult addVideo(@RequestBody Video video){
        videoService.save(video);
        return CommonResult.ok();
    }

    /**
     *  根据视频id来删除视频
     * @param id
     * @return
     */
    @DeleteMapping("/{id}")
    public CommonResult deleteVideo(@PathVariable String id){
        Video video = videoService.getById(id);
        String vid = video.getVideoSourceId();
        if (!StringUtils.isEmpty(vid)){
            vodClient.removeAlyVideo(vid);
        }
        videoService.removeById(id);
        return CommonResult.ok();
    }


}


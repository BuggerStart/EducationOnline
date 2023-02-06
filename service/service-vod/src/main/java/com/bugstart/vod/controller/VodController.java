package com.bugstart.vod.controller;

import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.vod.model.v20170321.DeleteVideoRequest;
import com.aliyuncs.vod.model.v20170321.GetVideoPlayAuthRequest;
import com.aliyuncs.vod.model.v20170321.GetVideoPlayAuthResponse;
import com.bugstart.edu.exception.MiliException;
import com.bugstart.utils.CommonResult;
import com.bugstart.vod.Utils.ConstantVodUtils;
import com.bugstart.vod.Utils.InitVodCilent;
import com.bugstart.vod.server.VodService;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author bugstart
 * @create 2023-01-31 16:57
 */
@RestController
@CrossOrigin
@RequestMapping("/eduvod/video")
public class VodController {

    @Resource
    private VodService vodService;

    /**
     * 上传视频到阿里云
     * @param file
     * @return
     */
    @PostMapping("/uploadAlyiVideo")
    public CommonResult uploadAlyiVideo(MultipartFile file){
        String videoId=vodService.uploadVideoAly(file);
        return CommonResult.ok().data("videoId",videoId);
    }

    /**
     *根据视频id来删除阿里云视频
     * @param id
     * @return
     */
    @DeleteMapping("removeAlyVideo/{id}")
    public CommonResult removeAlyVideo(@PathVariable String id) {
        try {
            //初始化对象
            DefaultAcsClient client = InitVodCilent.initVodClient(ConstantVodUtils.ACCESS_KEY_ID, ConstantVodUtils.ACCESS_KEY_SECRET);
            //创建删除视频request对象
            DeleteVideoRequest request = new DeleteVideoRequest();
            //向request设置视频id
            request.setVideoIds(id);
            //调用初始化对象的方法实现删除
            client.getAcsResponse(request);
            return CommonResult.ok();
        }catch(Exception e) {
            e.printStackTrace();
            throw new MiliException(20001,"删除视频失败");
        }
    }

    /**
     *删除多个阿里云视频的方法
     * @param videoIdList
     * @return
     */
    @DeleteMapping("delete-batch")
    public CommonResult deleteBatch(@RequestParam("videoIdList") List<String> videoIdList) {
        vodService.removeMoreAlyVideo(videoIdList);
        return CommonResult.ok();
    }

    //根据视频id获取视频凭证
    @GetMapping("getPlayAuth/{id}")
    public CommonResult getPlayAuth(@PathVariable String id) {
        try {
            //创建初始化对象
            DefaultAcsClient client =
                    InitVodCilent.initVodClient(ConstantVodUtils.ACCESS_KEY_ID, ConstantVodUtils.ACCESS_KEY_SECRET);
            //创建获取凭证request和response对象
            GetVideoPlayAuthRequest request = new GetVideoPlayAuthRequest();
            //向request设置视频id
            request.setVideoId(id);
            //调用方法得到凭证
            GetVideoPlayAuthResponse response = client.getAcsResponse(request);
            String playAuth = response.getPlayAuth();
            return CommonResult.ok().data("playAuth",playAuth);
        }catch(Exception e) {
            throw new MiliException(20001,"获取凭证失败");
        }
    }
}

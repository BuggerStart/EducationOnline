package com.bugstart.edu.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.bugstart.edu.client.VodClient;
import com.bugstart.edu.entity.Video;
import com.bugstart.edu.mapper.VideoMapper;
import com.bugstart.edu.service.VideoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 * 课程视频 服务实现类
 * </p>
 *
 * @author bugstart
 * @since 2023-01-29
 */
@Service
public class VideoServiceImpl extends ServiceImpl<VideoMapper, Video> implements VideoService {

    @Autowired
    private VodClient vodClient;
    @Override
    public void removeVideoByCourseId(String courseId) {

        QueryWrapper<Video> videoQueryWrapper = new QueryWrapper<>();
        videoQueryWrapper.eq("course_id",courseId);
        videoQueryWrapper.select("video_source_id");
        List<Video> videos = baseMapper.selectList(videoQueryWrapper);
        List<String> videoIds=new ArrayList<>();
        for (Video video:videos){
            String videoSourceId = video.getVideoSourceId();
            if (StringUtils.isEmpty(videoSourceId)){
                videoIds.add(videoSourceId);
            }
        }
        if (videoIds.size()>0){
            vodClient.deleteBatch(videoIds);
        }
        QueryWrapper<Video> wrapper = new QueryWrapper();
        wrapper.eq("course_id",courseId);
        baseMapper.delete(wrapper);
    }


}

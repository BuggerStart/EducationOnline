package com.bugstart.edu.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.bugstart.edu.entity.Video;

/**
 * <p>
 * 课程视频 服务类
 * </p>
 *
 * @author bugstart
 * @since 2023-01-29
 */
public interface VideoService extends IService<Video> {

    void removeVideoByCourseId(String courseId);


}

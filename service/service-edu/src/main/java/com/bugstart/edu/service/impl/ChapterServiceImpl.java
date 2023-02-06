package com.bugstart.edu.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.bugstart.edu.entity.Chapter;
import com.bugstart.edu.entity.Video;
import com.bugstart.edu.entity.chapter.ChapterVo;
import com.bugstart.edu.entity.chapter.VideoVo;
import com.bugstart.edu.exception.MiliException;
import com.bugstart.edu.mapper.ChapterMapper;
import com.bugstart.edu.service.ChapterService;
import com.bugstart.edu.service.VideoService;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 * 课程 服务实现类
 * </p>
 *
 * @author bugstart
 * @since 2023-01-29
 */
@Service
public class ChapterServiceImpl extends ServiceImpl<ChapterMapper, Chapter> implements ChapterService {

     @Resource
     private VideoService videoService;

    @Override
    public List<ChapterVo> getChapterVideoByCourseId(String courseId) {
        QueryWrapper<Chapter> wrapperChapter = new QueryWrapper<>();
        wrapperChapter.eq("course_id",courseId);
        List<Chapter> chapters = baseMapper.selectList(wrapperChapter);
        QueryWrapper<Video> wrapperVideo = new QueryWrapper<>();
        wrapperVideo.eq("course_id",courseId);
        List<Video> videos = videoService.list(wrapperVideo);
        List<ChapterVo> finalList=new ArrayList<>();
        for (Chapter chapter:chapters){
            ChapterVo chapterVo = new ChapterVo();
            BeanUtils.copyProperties(chapter,chapterVo);
            //封装video的集合
            List<VideoVo> videoList=new ArrayList<>();
            for (Video  video:videos){
               if (video.getChapterId().equals(chapter.getId())){
                   VideoVo videoVo = new VideoVo();
                   BeanUtils.copyProperties(video,videoVo);
                   videoList.add(videoVo);
               }
            }
            chapterVo.setChildren(videoList);
            finalList.add(chapterVo);
        }
        return finalList;
    }

    @Override
    public boolean deleteChapter(String chapterId) {
        QueryWrapper<Video> wrapper = new QueryWrapper();
        wrapper.eq("chapter_id",chapterId);
        int count = videoService.count(wrapper);
        if (count>0){
            throw new MiliException(20001,"存在子节点不能删除");
        }
        int result = baseMapper.deleteById(chapterId);
        return result>0;
    }

    @Override
    public void removeChapterByCourseId(String courseId) {
        QueryWrapper<Chapter> wrapper = new QueryWrapper<>();
        wrapper.eq("course_id",courseId);
        baseMapper.delete(wrapper);
    }

}

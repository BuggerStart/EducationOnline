package com.bugstart.edu.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.bugstart.edu.entity.Chapter;
import com.bugstart.edu.entity.chapter.ChapterVo;

import java.util.List;

/**
 * <p>
 * 课程 服务类
 * </p>
 *
 * @author bugstart
 * @since 2023-01-29
 */
public interface ChapterService extends IService<Chapter> {

    List<ChapterVo> getChapterVideoByCourseId(String courseId);


    boolean deleteChapter(String chapterId);

    void removeChapterByCourseId(String courseId);


}

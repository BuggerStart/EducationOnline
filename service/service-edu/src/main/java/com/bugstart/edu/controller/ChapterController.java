package com.bugstart.edu.controller;


import com.bugstart.edu.entity.Chapter;
import com.bugstart.edu.entity.chapter.ChapterVo;
import com.bugstart.edu.service.ChapterService;
import com.bugstart.utils.CommonResult;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
 * <p>
 * 课程 前端控制器
 * </p>
 *
 * @author bugstart
 * @since 2023-01-29
 */
@RestController
@CrossOrigin
@RequestMapping("/edu/chapter")
public class ChapterController {

    @Resource
    private ChapterService chapterService;

    /**
     * 根据课程id查询她的chapter和video
     * @param courseId
     * @return
     */
    @GetMapping("/getChapterVideo/{courseId}")
    public CommonResult getChapterVideo(@PathVariable String courseId){

        List<ChapterVo> list= chapterService.getChapterVideoByCourseId(courseId);
        if (StringUtils.isEmpty(list)){
            return CommonResult.error();
        }
        return CommonResult.ok().data("allChapterVideo",list);
    }

    /**
     * 增加章节
     * @param chapter
     * @return
     */
    @PostMapping("/addChapter")
    public CommonResult addChapter(@RequestBody Chapter chapter){
        chapterService.save(chapter);
        return CommonResult.ok();
    }

    /**
     * 根据chapterId查询
     * @param chapterId
     * @return
     */
    @GetMapping("getChapterInfo/{chapterId}")
    public CommonResult getChapterInfo(@PathVariable String chapterId){
        Chapter chapter = chapterService.getById(chapterId);
        return CommonResult.ok().data("chapter",chapter);
    }

    /**
     * 修改章节
     * @param chapter
     * @return
     */
    @PostMapping("updateChapter")
    public CommonResult updateChapter(@RequestBody Chapter chapter){
        chapterService.updateById(chapter);
        return CommonResult.ok();
    }

    /**
     * 根据id来删除章节
     * @param chapterId
     * @return
     */
    @DeleteMapping("/{chapterId}")
    public CommonResult deleteChapter(@PathVariable String chapterId){
       boolean flag= chapterService.deleteChapter(chapterId);
       if (flag){
           return CommonResult.ok();
       }
       return CommonResult.error();
    }
}


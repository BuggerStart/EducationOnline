package com.bugstart.edu.controller;


import com.bugstart.edu.entity.subject.OneSubject;
import com.bugstart.edu.service.SubjectService;
import com.bugstart.utils.CommonResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.util.List;

/**
 * <p>
 * 课程科目 前端控制器
 * </p>
 *
 * @author bugstart
 * @since 2023-01-28
 */
@RestController
@CrossOrigin
@RequestMapping("/edu/subject")
public class SubjectController {

    @Resource
    private SubjectService subjectService;

    /**
     * 根据Excel表格来添加课程
     * @param file
     * @return
     */
    @PostMapping("/addSubject")
    public CommonResult addSubject(MultipartFile file){
        subjectService.saveSubject(file,subjectService);
        return CommonResult.ok();
    }

    /**
     * 获取课程的分类列表 树形列表
     * @return
     */
    @GetMapping("/getAllSubject")
    public CommonResult getAllSubject(){
        List<OneSubject> list= subjectService.getAllOneTwoSubject();
        return CommonResult.ok().data("list",list);
    }
}


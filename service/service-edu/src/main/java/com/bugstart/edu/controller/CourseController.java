package com.bugstart.edu.controller;


import com.bugstart.edu.entity.Course;
import com.bugstart.edu.entity.vo.CourseInfoVo;
import com.bugstart.edu.entity.vo.CoursePublishVo;
import com.bugstart.edu.service.CourseService;
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
@CrossOrigin
@RestController
@RequestMapping("/edu/course")
public class CourseController {

    @Resource
    private CourseService courseService;

    /**
     * 课程列表
     * @return
     */
    @GetMapping
    public CommonResult getCourseList(){
        List<Course> list =courseService.list(null);
        return CommonResult.ok().data("list", list);
    }

    /**
     * 添加课程基本信息的方法
     * @param courseInfoVo
     * @return
     */
    @PostMapping("addCourseInfo")
    private CommonResult addCourseInfo(@RequestBody CourseInfoVo courseInfoVo){
        String id=courseService.saveCourseInfo(courseInfoVo);
        if (StringUtils.isEmpty(id)){
            return CommonResult.error();
        }
        return CommonResult.ok().data("courseId",id);
    }

    /**
     * 根据课程id查询课程基本信息
     * @param courseId
     * @return
     */
    @GetMapping("/getCourseInfo/{courseId}")
    public CommonResult getCourseInfo(@PathVariable String courseId){
        CourseInfoVo courseInfoVo= courseService.getCourseInfo(courseId);
        if (StringUtils.isEmpty(courseInfoVo)){
            return CommonResult.error();
        }
        return CommonResult.ok().data("courseInfoVo",courseInfoVo);
    }

    /**
     * 修改课程信息
     * @param courseInfoVo
     * @return
     */
    @PostMapping("/updateCourseInfo")
    public CommonResult updateCourseInfo(@RequestBody CourseInfoVo courseInfoVo){
        courseService.updateCourseInfo(courseInfoVo);
        return CommonResult.ok();
    }

    /**
     * 根据课程id查询课程确认信息
     * @param id
     * @return
     */
    @GetMapping("getPublishCourseInfo/{id}")
    public CommonResult getPublishCourseInfo(@PathVariable String id){
       CoursePublishVo coursePublishVo= courseService.publishCourseInfo(id);
       return CommonResult.ok().data("publishCourse",coursePublishVo);
    }

    /**
     * 课程最终发布，修改课程状态
     * @param id
     * @return
     */
    @PostMapping("publishCourse/{id}")
    public CommonResult publishCourse(@PathVariable String id){
        Course course = new Course();
        course.setId(id);
        course.setStatus("Normal");
        courseService.updateById(course);
        return CommonResult.ok();
    }

    /**
     * 根据课程id来删除课程
     * @param courseId
     * @return
     */
    @DeleteMapping("{courseId}")
    public CommonResult deleteCourse(@PathVariable String courseId){
        courseService.removeCourse(courseId);
        return CommonResult.ok();
    }
}


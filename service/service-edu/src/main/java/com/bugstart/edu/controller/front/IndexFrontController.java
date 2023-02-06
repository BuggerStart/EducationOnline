package com.bugstart.edu.controller.front;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.bugstart.edu.entity.Course;
import com.bugstart.edu.entity.Teacher;
import com.bugstart.edu.service.CourseService;
import com.bugstart.edu.service.TeacherService;
import com.bugstart.utils.CommonResult;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author bugstart
 * @create 2023-02-01 16:29
 */
@RestController
@CrossOrigin
@RequestMapping("/edu/indexfront")
public class IndexFrontController {

    @Resource
    private CourseService courseService;

    @Resource
    private TeacherService teacherService;


    @GetMapping("/index")
    public CommonResult index(){
        QueryWrapper<Course> courseQueryWrapper = new QueryWrapper<>();
        courseQueryWrapper.orderByDesc("id");
        courseQueryWrapper.last("limit 8");
        List<Course> eduList = courseService.list(courseQueryWrapper);

        QueryWrapper<Teacher> teacherQueryWrapper = new QueryWrapper<>();
        teacherQueryWrapper.orderByDesc("id");
        teacherQueryWrapper.last("limit 4");
        List<Teacher> teacherList = teacherService.list(teacherQueryWrapper);

        return CommonResult.ok().data("eduList",eduList).data("teacherList",teacherList);
    }



}

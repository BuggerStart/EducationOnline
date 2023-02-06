package com.bugstart.edu.controller.front;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.bugstart.edu.entity.Course;
import com.bugstart.edu.entity.Teacher;
import com.bugstart.edu.service.CourseService;
import com.bugstart.edu.service.TeacherService;
import com.bugstart.utils.CommonResult;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

/**
 * @author bugstart
 * @create 2023-02-03 14:33
 */
@RestController
@CrossOrigin
@RequestMapping("/edu/teacherfront")
public class TeacherFrontController {

    @Resource
    private TeacherService teacherService;

    @Resource
    private CourseService courseService;

    @PostMapping("/getTeacherFrontList/{page}/{limit}")
    public CommonResult getTeacherFrontList(@PathVariable("page") long page,
                                            @PathVariable("limit") long limit){
        Page<Teacher> teacherPage = new Page<>(page, limit);
//        System.out.println("___________________"+teacherPage.toString());
        Map<String,Object> map= teacherService.getTeacherFrontList(teacherPage);
        return CommonResult.ok().data(map);
    }

    @GetMapping("/getTeacherFrontInfo/{teacherId}")
    public CommonResult getTeacherFrontInfo(@PathVariable("teacherId") String teacherId){
        Teacher teacher = teacherService.getById(teacherId);
        QueryWrapper<Course> wrapper = new QueryWrapper<>();
        wrapper.eq("teacher_id",teacherId);
        List<Course> courseList = courseService.list(wrapper);
        return CommonResult.ok().data("teacher",teacher).data("courseList",courseList);
    }
}

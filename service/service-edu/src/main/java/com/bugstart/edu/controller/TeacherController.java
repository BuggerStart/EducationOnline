package com.bugstart.edu.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.bugstart.edu.entity.Teacher;
import com.bugstart.edu.entity.TeacherQuery;
import com.bugstart.edu.service.TeacherService;
import com.bugstart.utils.CommonResult;
import lombok.extern.slf4j.Slf4j;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author bugstart
 * @since 2023-01-25
 */
@RestController
@CrossOrigin(allowCredentials = "true")
@RequestMapping("/edu/teacher")
@Slf4j
public class TeacherController {

    @Resource
    private TeacherService teacherService;


    /**
     * 返回所有的讲师集合
     * @return
     */
    @GetMapping("/getall")
    public CommonResult getAllTeacher(){
        List<Teacher> list = teacherService.list(null);
        //用于测试自定义的异常类
//        int i=10/0;

        Map<String,Object> map=new HashMap<>();
        map.put("data",list);
        return CommonResult.ok().data(map);
    }

    /**
     * 根据id来删除讲师
     * @param id
     * @return
     */
    @DeleteMapping("/delete/{id}")
    public CommonResult deleteById(@PathVariable String id){
        boolean isDelete = teacherService.removeById(id);
        if (isDelete){
            return CommonResult.ok();
        }
        return CommonResult.error();
    }

    /**
     * 进行分页操作
     * @param current
     * @param limit
     * @return
     */
    @GetMapping("/getpage/{current}/{limit}")
    public CommonResult pageListTeacher(@PathVariable("current") long current,
                                        @PathVariable("limit") long limit){
        Page<Teacher> page = new Page<>(current,limit);
        teacherService.page(page,null);
        long total = page.getTotal();
        List<Teacher> records = page.getRecords();
        Map<String,Object> map=new HashMap<>();
        map.put("total",total);
        map.put("data",records);
        return CommonResult.ok().data(map);
    }

    /**
     * 按条件进行查询，将查询结果进行分页显示
     * @param current
     * @param limit
     * @param teacherQuery
     * @return
     */
    @PostMapping("/condition/{current}/{limit}")
    public CommonResult pageQuery(@PathVariable("current") long current,
                          @PathVariable("limit") long limit,
                          @RequestBody(required = false) TeacherQuery teacherQuery) {
        Page<Teacher> page = new Page<>(current,limit);
        QueryWrapper<Teacher> wrapper = new QueryWrapper<>();
        if (StringUtils.isEmpty(teacherQuery)){
            return CommonResult.error();
        }
        String name = teacherQuery.getName();
        Integer level = teacherQuery.getLevel();
        String begin = teacherQuery.getBegin();
        String end = teacherQuery.getEnd();
        if (!StringUtils.isEmpty(name)){
            wrapper.like("name",name);
        }
        if (!StringUtils.isEmpty(level)){
            wrapper.eq("level",level);
        }
        if (!StringUtils.isEmpty(begin)){
            wrapper.ge("gmt_create",begin);
        }
        if (!StringUtils.isEmpty(end)){
            wrapper.le("gmt_create",begin);
        }
        teacherService.page(page,wrapper);
        long total = page.getTotal();
        List<Teacher> records = page.getRecords();
        HashMap<String, Object> map = new HashMap<>();
        map.put("total",total);
        map.put("rows",records);
        return CommonResult.ok().data(map);
    }

    /**
     * 添加讲师功能
     * @param teacher
     * @return
     */
    @PostMapping("/add")
    public CommonResult add(@RequestBody Teacher teacher){
        boolean isAdd = teacherService.save(teacher);
        if (isAdd){
            return CommonResult.ok();
        }
        return CommonResult.error();
    }

    /**
     * 根据id来查询讲师
     * @param id
     * @return
     */
    @GetMapping("/query/{id}")
    public CommonResult queryById(@PathVariable("id") Integer id){
        Teacher teacher = teacherService.getById(id);
        if (StringUtils.isEmpty(teacher)){
            return CommonResult.error();
        }
        Map<String,Object> map=new HashMap<>();
        map.put("teacher",teacher);
        return CommonResult.ok().data(map);
    }

    /**
     * 对讲师的信息进行更新
     * @param teacher
     * @return
     */
    @PostMapping("/update")
    public CommonResult updateTeacher(@RequestBody Teacher teacher){
        boolean isUpdate = teacherService.updateById(teacher);
        if (isUpdate){
            return CommonResult.ok();
        }
            return CommonResult.error();
    }

}


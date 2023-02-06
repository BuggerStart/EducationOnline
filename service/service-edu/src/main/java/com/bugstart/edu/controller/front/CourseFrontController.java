package com.bugstart.edu.controller.front;


import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.bugstart.edu.client.OrdersClient;
import com.bugstart.edu.entity.Course;
import com.bugstart.edu.entity.chapter.ChapterVo;
import com.bugstart.edu.entity.frontvo.CourseFrontVo;
import com.bugstart.edu.entity.frontvo.CourseWebVo;
import com.bugstart.edu.entity.vo.CourseInfoVo;
import com.bugstart.edu.service.ChapterService;
import com.bugstart.edu.service.CourseService;
import com.bugstart.utils.CommonResult;
import com.bugstart.utils.JwtUtils;
import com.bugstart.utils.ordervo.CourseWebVoOrder;
import org.springframework.beans.BeanUtils;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;

/**
 * @author bugstart
 * @create 2023-02-03 17:10
 */
@RestController
@CrossOrigin
@RequestMapping("/edu/coursefront")
public class CourseFrontController {
    @Resource
    private CourseService courseService;

    @Resource
    private  ChapterService chapterService;

    @Resource
    private OrdersClient ordersClient;

    /**
     * 条件查询带分页查询课程
     * @param page
     * @param limit
     * @param courseFrontVo
     * @return
     */
    @PostMapping("/getFrontCourseList/{page}/{limit}")
    public CommonResult getFrontCourseList(@PathVariable("page") long page, @PathVariable("limit") long limit,
                                           @RequestBody(required = false) CourseFrontVo courseFrontVo){
        Page<Course> pages = new Page<>(page, limit);
        Map<String,Object> map=courseService.getCourseFrontList(pages,courseFrontVo);
        return CommonResult.ok().data(map);
    }
    @GetMapping("/getFrontCourseInfo/{courseId}")
    public CommonResult getFrontCourseInfo(@PathVariable String courseId){
        CourseWebVo courseWebVo=courseService.getBaseCourseInfo(courseId);
        List<ChapterVo> chapterVideoList = chapterService.getChapterVideoByCourseId(courseId);
        return CommonResult.ok().data("courseWebVo",courseWebVo).data("chapterVideoList",chapterVideoList);
    }

    //根据课程id查询课程信息
    @PostMapping("getCourseInfoOrder/{id}")
    public CourseWebVoOrder getCourseInfoOrder(@PathVariable String id) {
        CourseWebVo courseInfo = courseService.getBaseCourseInfo(id);
        CourseWebVoOrder courseWebVoOrder = new CourseWebVoOrder();
        BeanUtils.copyProperties(courseInfo,courseWebVoOrder);
        return courseWebVoOrder;
    }

    @GetMapping("/getCourseInfo/{id}")
    public CommonResult getCourseInfo(@PathVariable("id")String id, HttpServletRequest request){
        CourseInfoVo courseInfo = courseService.getCourseInfo(id);
        List<ChapterVo> chapterVideoList = chapterService.getChapterVideoByCourseId(id);

        boolean buyCourse = ordersClient.isBuyCourse(JwtUtils.getMemberIdByJwtToken(request), id);
        return CommonResult.ok().data("courseFrontInfo",courseInfo).data("chapterVideoList",chapterVideoList).data("isbuy", buyCourse);
    }

}

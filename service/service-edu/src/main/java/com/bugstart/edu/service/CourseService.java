package com.bugstart.edu.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.bugstart.edu.entity.Course;
import com.baomidou.mybatisplus.extension.service.IService;
import com.bugstart.edu.entity.frontvo.CourseFrontVo;
import com.bugstart.edu.entity.frontvo.CourseWebVo;
import com.bugstart.edu.entity.vo.CourseInfoVo;
import com.bugstart.edu.entity.vo.CoursePublishVo;

import java.util.Map;

/**
 * <p>
 * 课程 服务类
 * </p>
 *
 * @author bugstart
 * @since 2023-01-29
 */
public interface CourseService extends IService<Course> {

    String saveCourseInfo(CourseInfoVo courseInfoVo);

    CourseInfoVo getCourseInfo(String courseId);

    void updateCourseInfo(CourseInfoVo courseInfoVo);

    CoursePublishVo publishCourseInfo(String id);

    void removeCourse(String courseId);

    Map<String, Object> getCourseFrontList(Page<Course> pages, CourseFrontVo courseFrontVo);

    CourseWebVo getBaseCourseInfo(String courseId);
}

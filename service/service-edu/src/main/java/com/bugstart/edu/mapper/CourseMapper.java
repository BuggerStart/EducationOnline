package com.bugstart.edu.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.bugstart.edu.entity.Course;
import com.bugstart.edu.entity.frontvo.CourseWebVo;
import com.bugstart.edu.entity.vo.CoursePublishVo;

/**
 * <p>
 * 课程 Mapper 接口
 * </p>
 *
 * @author bugstart
 * @since 2023-01-29
 */
public interface CourseMapper extends BaseMapper<Course> {

    public CoursePublishVo getPublishCourseInfo(String courseId);

    CourseWebVo getBaseCourseInfo(String courseId);
}

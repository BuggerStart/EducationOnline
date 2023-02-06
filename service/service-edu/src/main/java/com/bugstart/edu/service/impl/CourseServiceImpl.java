package com.bugstart.edu.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.bugstart.edu.entity.Course;
import com.bugstart.edu.entity.CourseDescription;
import com.bugstart.edu.entity.frontvo.CourseFrontVo;
import com.bugstart.edu.entity.frontvo.CourseWebVo;
import com.bugstart.edu.entity.vo.CourseInfoVo;
import com.bugstart.edu.entity.vo.CoursePublishVo;
import com.bugstart.edu.exception.MiliException;
import com.bugstart.edu.mapper.CourseMapper;
import com.bugstart.edu.service.ChapterService;
import com.bugstart.edu.service.CourseDescriptionService;
import com.bugstart.edu.service.CourseService;
import com.bugstart.edu.service.VideoService;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * 课程 服务实现类
 * </p>
 *
 * @author bugstart
 * @since 2023-01-29
 */
@Service
public class CourseServiceImpl extends ServiceImpl<CourseMapper, Course> implements CourseService {

    @Resource
    private CourseDescriptionService courseDescriptionService;

    @Resource
    private VideoService   videoService;

    @Resource
    private  ChapterService chapterService;

    @Override
    public String saveCourseInfo(CourseInfoVo courseInfoVo) {
        Course course = new Course();
        BeanUtils.copyProperties(courseInfoVo,course);
        int flag = baseMapper.insert(course);
        if (flag==0){
            throw new MiliException(20001,"添加课程失败");
        }
        String cid=course.getId();
        CourseDescription courseDescription = new CourseDescription();
        BeanUtils.copyProperties(courseInfoVo,courseDescription);
        courseDescription.setId(cid);
        courseDescriptionService.save(courseDescription);
        return cid;
    }

    @Override
    public CourseInfoVo getCourseInfo(String courseId) {
        CourseInfoVo courseInfoVo = new CourseInfoVo();
        Course course = baseMapper.selectById(courseId);
        BeanUtils.copyProperties(course,courseInfoVo);
        CourseDescription courseDescriptionServiceById = courseDescriptionService.getById(courseId);
        courseInfoVo.setDescription(courseDescriptionServiceById.getDescription());
        return courseInfoVo;
    }

    @Override
    public void updateCourseInfo(CourseInfoVo courseInfoVo) {
        Course course = new Course();
        BeanUtils.copyProperties(courseInfoVo,course);
        int isUpdate = baseMapper.updateById(course);
        if (isUpdate==0){
            throw new MiliException(20001,"修改课程失败");
        }
        CourseDescription courseDescription = new CourseDescription();
        BeanUtils.copyProperties(courseInfoVo,courseDescription);
        courseDescriptionService.updateById(courseDescription);
    }

    @Override
    public CoursePublishVo publishCourseInfo(String id) {

        CoursePublishVo publishCourseInfo = baseMapper.getPublishCourseInfo(id);
        return publishCourseInfo;
    }

    @Override
    public void removeCourse(String courseId) {
        videoService.removeVideoByCourseId(courseId);
        chapterService.removeChapterByCourseId(courseId);
        courseDescriptionService.removeById(courseId);
        int result = baseMapper.deleteById(courseId);
        if (result==0){
            throw new MiliException(20001,"删除失败");
        }

    }

    @Override
    public Map<String, Object> getCourseFrontList(Page<Course> pages, CourseFrontVo courseFrontVo) {
        QueryWrapper<Course> wrapper = new QueryWrapper<>();
        if (!StringUtils.isEmpty(courseFrontVo.getSubjectParentId())){
            wrapper.eq("subject_parent_id",courseFrontVo.getSubjectParentId());
        }if (!StringUtils.isEmpty(courseFrontVo.getSubjectId())){
            wrapper.eq("subject_id",courseFrontVo.getSubjectId());
        }
        if (!StringUtils.isEmpty(courseFrontVo.getBuyCountSort())){
            wrapper.orderByDesc("buy_count");
        }
        if (!StringUtils.isEmpty(courseFrontVo.getGmtCreateSort())){
            wrapper.orderByDesc("gmt_create");
        }
        if (!StringUtils.isEmpty(courseFrontVo.getPriceSort())){
            wrapper.orderByDesc("price");
        }
        baseMapper.selectPage(pages,wrapper);
        List<Course> records = pages.getRecords();
        long current = pages.getCurrent();
        long pages1 = pages.getPages();
        long size = pages.getSize();
        long total = pages.getTotal();
        boolean hasNext = pages.hasNext();//下一页
        boolean hasPrevious = pages.hasPrevious();//上一页

        //把分页数据获取出来，放到map集合
        Map<String, Object> map = new HashMap<>();
        map.put("items", records);
        map.put("current", current);
        map.put("pages", pages1);
        map.put("size", size);
        map.put("total", total);
        map.put("hasNext", hasNext);
        map.put("hasPrevious", hasPrevious);

        //map返回
        return map;
    }

    @Override
    public CourseWebVo getBaseCourseInfo(String courseId) {
        return baseMapper.getBaseCourseInfo(courseId);
    }
}

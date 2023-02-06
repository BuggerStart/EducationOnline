package com.bugstart.edu.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.bugstart.edu.entity.Teacher;

import java.util.Map;

/**
 * <p>
 * 讲师 服务类
 * </p>
 *
 * @author bugstart
 * @since 2023-01-25
 */
public interface TeacherService extends IService<Teacher> {

    Map<String, Object> getTeacherFrontList(Page<Teacher> teacherPage);

}

package com.bugstart.edu.service;

import com.bugstart.edu.entity.Subject;
import com.baomidou.mybatisplus.extension.service.IService;
import com.bugstart.edu.entity.subject.OneSubject;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/**
 * <p>
 * 课程科目 服务类
 * </p>
 *
 * @author bugstart
 * @since 2023-01-28
 */
public interface SubjectService extends IService<Subject> {

    void saveSubject(MultipartFile file, SubjectService subjectService);

    List<OneSubject> getAllOneTwoSubject();
}

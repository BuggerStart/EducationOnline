package com.bugstart.edu.service.impl;

import com.alibaba.excel.EasyExcel;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.bugstart.edu.entity.Subject;
import com.bugstart.edu.entity.excel.SubjectData;
import com.bugstart.edu.entity.subject.OneSubject;
import com.bugstart.edu.entity.subject.TwoSubject;
import com.bugstart.edu.listener.SubjectExcelListener;
import com.bugstart.edu.mapper.SubjectMapper;
import com.bugstart.edu.service.SubjectService;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 * 课程科目 服务实现类
 * </p>
 *
 * @author bugstart
 * @since 2023-01-28
 */
@Service
public class SubjectServiceImpl extends ServiceImpl<SubjectMapper, Subject> implements SubjectService {

    @Override
    public void saveSubject(MultipartFile file, SubjectService subjectService) {
        try {
            InputStream inputStream = file.getInputStream();
            EasyExcel.read(inputStream, SubjectData.class,new SubjectExcelListener(subjectService)).sheet().doRead();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    @Override
    public List<OneSubject> getAllOneTwoSubject() {
        QueryWrapper<Subject> wrapperOne=new QueryWrapper<>();
        wrapperOne.eq("parent_id","0");
        //1、查询所有一级分类
        List<Subject> oneSubjectList = baseMapper.selectList(wrapperOne);
        //2、查询所有二级分类
        QueryWrapper<Subject> wrapperTwo=new QueryWrapper<>();
        wrapperTwo.ne("parent_id","0");
        List<Subject> twoSubjectList = baseMapper.selectList(wrapperTwo);
        //创建list集合，用于存储最终封装数据
        List<OneSubject> finalSubjectList=new ArrayList<>();
        //封装一级
        for (Subject oneSubject:oneSubjectList){
            OneSubject newOneSubject = new OneSubject();
            newOneSubject.setId(oneSubject.getId());
            newOneSubject.setTitle(oneSubject.getTitle());
            List<TwoSubject> twoFinalSubjectList=new ArrayList<>();
            for (Subject subject:twoSubjectList){
                if (subject.getParentId().equals(oneSubject.getId())){
                    TwoSubject twoSubject = new TwoSubject();
                    twoSubject.setId(subject.getId());
                    twoSubject.setTitle(subject.getTitle());
                    twoFinalSubjectList.add(twoSubject);
                }
            }
                newOneSubject.setChildren(twoFinalSubjectList);
                finalSubjectList.add(newOneSubject);
            }

        return finalSubjectList;
    }
}

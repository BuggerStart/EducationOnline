package com.bugstart.edu.entity.vo;

import lombok.Data;

/**
 * @author bugstart
 * @create 2023-01-30 21:58
 */
@Data
public class CoursePublishVo {
    private String id;
    private String title;
    private String cover;
    private Integer lessonNum;
    private String subjectLevelOne;
    private String subjectLevelTwo;
    private String teacherName;
    private String price;//只用于显示
}


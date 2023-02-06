package com.bugstart.edu.entity.vo;

import lombok.Data;

import java.math.BigDecimal;

/**
 * @author bugstart
 * @create 2023-01-29 21:47
 */
@Data
public class CourseInfoVo {

    private String id;

    private String teacherId;

    private String subjectId;


    private String subjectParentId;

    private String title;

    private BigDecimal price;

    private Integer lessonNum;

    private String cover;

    private String description;




}

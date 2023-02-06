package com.bugstart.edu.entity.frontvo;

import lombok.Data;

/**
 * @author bugstart
 * @create 2023-02-03 17:15
 */
@Data
public class CourseFrontVo {

    private String title;

    private String teacherId;

    private String subjectParentId;

    private String subjectId;

    private String buyCountSort;

    private String gmtCreateSort;

    private String priceSort;
}

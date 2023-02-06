package com.bugstart.edu.entity;

import lombok.Data;

import java.io.Serializable;

/**
 * @author bugstart
 * @create 2023-01-25 23:15
 */
@Data
public class TeacherQuery implements Serializable {
    private static final long serialVersionUID = 1L;

    private String name ;

    private Integer level;

    private String begin;

    private String end;
}

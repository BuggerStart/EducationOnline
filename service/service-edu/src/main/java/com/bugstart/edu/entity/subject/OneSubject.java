package com.bugstart.edu.entity.subject;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

/**
 * @author bugstart
 * @create 2023-01-29 16:06
 */
@Data
public class OneSubject {

    private String id;

    private String title;

    private List<TwoSubject> children=new ArrayList<>();

}


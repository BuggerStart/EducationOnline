package com.bugstart.edu.entity.chapter;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

/**
 * @author bugstart
 * @create 2023-01-30 17:03
 */
@Data
public class ChapterVo {

    private String id;

    private String title;

    private List<VideoVo> children=new ArrayList<>();

}

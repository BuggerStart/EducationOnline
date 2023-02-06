package com.bugstart.staservice.controller;


import com.baomidou.mybatisplus.extension.api.R;
import com.bugstart.staservice.service.StatisticsDailyService;
import com.bugstart.utils.CommonResult;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.Map;

/**
 * <p>
 * 网站统计日数据 前端控制器
 * </p>
 *
 * @author bugstart
 * @since 2023-02-05
 */
@RestController
@CrossOrigin
@RequestMapping("/staservice/sta")
public class StatisticsDailyController {

    @Resource
    private StatisticsDailyService staService;

    /**
     * 统计某一天注册人数，生成统计数据
     * @param day
     * @return
     */
    @PostMapping("/registerCount/{day}")
    public CommonResult registerCount(@PathVariable("day") String day){
        staService.registerCount(day);
        return CommonResult.ok();
    }

    /**
     * 图表显示，返回两部分数据，日期json数组，数量json数组
     * @param type
     * @param begin
     * @param end
     * @return
     */
    @GetMapping("showData/{type}/{begin}/{end}")
    public CommonResult showData(@PathVariable String type, @PathVariable String begin,
                      @PathVariable String end) {
        Map<String,Object> map = staService.getShowData(type,begin,end);
        return CommonResult.ok().data(map);
    }
}


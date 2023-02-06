package com.bugstart.edu.client;

import com.bugstart.utils.CommonResult;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @author bugstart
 * @create 2023-01-31 23:17
 */
@Component
public class VodFileDegradeFeignClient implements VodClient{
    @Override
    public CommonResult removeAlyVideo(String id) {
        return CommonResult.error().message("删除视频出错了");
    }

    @Override
    public CommonResult deleteBatch(List<String> videoIdList) {
        return CommonResult.error().message("删除多个视频出错了");
    }
}

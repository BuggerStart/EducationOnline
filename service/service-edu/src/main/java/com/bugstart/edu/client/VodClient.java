package com.bugstart.edu.client;

import com.bugstart.utils.CommonResult;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

/**
 * @author bugstart
 * @create 2023-01-31 21:35
 */
@Component
@FeignClient(value = "service-vod",fallback = VodFileDegradeFeignClient.class)
public interface VodClient {

    @DeleteMapping("/eduvod/video/removeAlyVideo/{id}")
    public CommonResult removeAlyVideo(@PathVariable("id") String id);

    @DeleteMapping("/eduvod/video/delete-batch")
    public CommonResult deleteBatch(@RequestParam("videoIdList") List<String> videoIdList);
}

package com.bugstart.staservice.client;

import com.bugstart.utils.CommonResult;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

/**
 * @author bugstart
 * @create 2023-02-05 21:17
 */
@Component
@FeignClient("service-ucenter")
public interface UcenterClient {

    @GetMapping("/educenter/member/countRegister/{day}")
    public CommonResult countRegister(@PathVariable String day);
}

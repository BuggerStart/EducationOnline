package com.bugstart.msmservice.controller;

import com.bugstart.msmservice.service.MsmService;
import com.bugstart.msmservice.utils.RandomUtil;
import com.bugstart.utils.CommonResult;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

/**
 * @author bugstart
 * @create 2023-02-02 0:48
 */
@RestController
@CrossOrigin
@RequestMapping("/edumsm/msm")
public class MsmController {

    @Resource
    private RedisTemplate redisTemplate;
    @Resource
    private MsmService msmService;

    @GetMapping("/send/{phone}")
    public CommonResult sendMsm(@PathVariable("phone")String phone){
        String code = RandomUtil.getFourBitRandom();
        System.out.println("code:"+code);
        Map<String,Object> param=new HashMap<>();
        param.put("code",code);
        boolean isSend=msmService.send(param,phone);
        if (isSend){
            redisTemplate.opsForValue().set(phone,code,5, TimeUnit.MINUTES);
            return CommonResult.ok();
        }
        return CommonResult.error().message("短信发送失败");
    }
}

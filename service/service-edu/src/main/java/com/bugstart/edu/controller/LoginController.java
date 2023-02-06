package com.bugstart.edu.controller;

import com.bugstart.utils.CommonResult;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

/**
 * @author bugstart
 * @create 2023-01-27 15:41
 */
@RestController
@CrossOrigin(allowCredentials = "true")
@RequestMapping("/edu/user")
public class LoginController {

    @PostMapping("/login")
    public CommonResult login(){

        Map<String,Object> map=new HashMap<>();
        map.put("token","admin");
        return CommonResult.ok().data(map);
    }

    @GetMapping("/info")
    public CommonResult info(){
        Map<String,Object> map=new HashMap<>();
        map.put("roles","[admin]");
        map.put("name","admin");
        map.put("avatar","https://wpimg.wallstcn.com/f778738c-e4f8-4870-b634-56703b4acafe.gif");

        return CommonResult.ok().data(map);
    }
}

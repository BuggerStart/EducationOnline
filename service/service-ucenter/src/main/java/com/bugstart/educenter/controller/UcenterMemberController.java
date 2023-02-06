package com.bugstart.educenter.controller;


import com.bugstart.educenter.entity.UcenterMember;
import com.bugstart.educenter.entity.vo.RegisterVo;
import com.bugstart.educenter.service.UcenterMemberService;
import com.bugstart.utils.CommonResult;
import com.bugstart.utils.JwtUtils;
import com.bugstart.utils.ordervo.UcenterMemberOrder;
import org.springframework.beans.BeanUtils;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

/**
 * <p>
 * 会员表 前端控制器
 * </p>
 *
 * @author bugstart
 * @since 2023-02-02
 */
@RestController
@CrossOrigin
@RequestMapping("/educenter/member")
public class UcenterMemberController {

    @Resource
    private UcenterMemberService memberService;

    /**
     * 登录
     * @param member
     * @return
     */
    @PostMapping("/login")
    public CommonResult loginUser(@RequestBody UcenterMember member){
        //member对象封装手机号和密码
        //调用service方法实现登录
        //返回token值，使用jwt生成
        String token= memberService.login(member);
        return CommonResult.ok().data("token",token);
    }

    /**
     * 注册
     * @param registerVo
     * @return
     */
    @PostMapping("/register")
    public CommonResult registerUser(@RequestBody RegisterVo registerVo){
        memberService.register(registerVo);
        return CommonResult.ok();
    }


    /**
     * 根据token获取用户信息
     * @param request
     * @return
     */
    @GetMapping("/getMemberInfo")
    public CommonResult getMemberInfo(HttpServletRequest request){
        String memberId = JwtUtils.getMemberIdByJwtToken(request);
        UcenterMember member = memberService.getById(memberId);
        return CommonResult.ok().data("userInfo",member);
    }
    /**
     * 根据用户id获取用户信息
     */
    @PostMapping("getUserInfoOrder/{id}")
    public UcenterMemberOrder getUserInfoOrder(@PathVariable String id) {
        UcenterMember member = memberService.getById(id);
        //把member对象里面值复制给UcenterMemberOrder对象
        UcenterMemberOrder ucenterMemberOrder = new UcenterMemberOrder();
        BeanUtils.copyProperties(member,ucenterMemberOrder);
        return ucenterMemberOrder;
    }

    /**
     * 查询某一天注册人数
     * @param day
     * @return
     */
    @GetMapping("/countRegister/{day}")
    public CommonResult countRegister(@PathVariable String day){
         Integer count= memberService.countRegisterDay(day);
         return CommonResult.ok().data("countRegister",count);
    }

}


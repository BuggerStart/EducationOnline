package com.bugstart.eduorder.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.bugstart.eduorder.entity.Order;
import com.bugstart.eduorder.service.OrderService;
import com.bugstart.utils.CommonResult;
import com.bugstart.utils.JwtUtils;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

/**
 * <p>
 * 订单 前端控制器
 * </p>
 *
 * @author bugstart
 * @since 2023-02-04
 */
@CrossOrigin
@RestController
@RequestMapping("/eduorder/order")
public class OrderController {

    @Resource
    private OrderService orderService;

    /**
     * 根据课程id和用户id创建订单，返回订单id
     */
    @PostMapping("/createOrder/{courseId}")
    public CommonResult saveOrder(@PathVariable String courseId, HttpServletRequest request){
        String orderNo=orderService.createOrders(courseId, JwtUtils.getMemberIdByJwtToken(request));
        return CommonResult.ok().data("orderId",orderNo);
    }

    /**
     * 根据订单id查询订单信息
     * @param orderId
     * @return
     */
    @GetMapping("/getOrderInfo/{orderId}")
    public CommonResult getOrderInfo(@PathVariable String orderId){
        QueryWrapper<Order> wrapper = new QueryWrapper<>();
        wrapper.eq("order_no",orderId);
        Order order = orderService.getOne(wrapper);
        return CommonResult.ok().data("item",order);
    }

    /**
     * 根据课程id和用户id查询订单表中的订单状态
     * @param courseId
     * @param memberId
     * @return
     */
    @GetMapping("/isBuyCourse/{courseId}/{memberId}")
    public boolean isBuyCourse(@PathVariable("courseId") String courseId,@PathVariable("memberId") String memberId){
        QueryWrapper<Order> wrapper = new QueryWrapper<>();
        wrapper.eq("course_id",courseId);
        wrapper.eq("member_id",memberId);
        wrapper.eq("status",1);
        int count = orderService.count(wrapper);
        if (count>0){
            return true;
        }else{
            return false;
        }

    }
}


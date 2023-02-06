package com.bugstart.eduorder.controller;


import com.bugstart.eduorder.service.PayLogService;
import com.bugstart.utils.CommonResult;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.Map;

/**
 * <p>
 * 支付日志表 前端控制器
 * </p>
 *
 * @author bugstart
 * @since 2023-02-04
 */
@CrossOrigin
@RestController
@RequestMapping("/eduorder/paylog")
public class PayLogController {

    @Resource
    private PayLogService payLogService;

    /**
     * 生成微信支付二维码接口
     * 参数是订单号
     * @param orderNo
     * @return
     */
    @GetMapping("/createNative/{orderNo}")
    public CommonResult createNative(@PathVariable String orderNo){
        Map map= payLogService.createNative(orderNo);
        return CommonResult.ok().data(map);
    }

    /**
     * 查询订单的支付状态
     * @param orderNo
     * @return
     */
    @GetMapping("/queryPayStatus/{orderNo}")
    public CommonResult queryPayStatus(@PathVariable("orderNo")  String orderNo){
            Map<String,String> map= payLogService.queryPayStatus(orderNo);
            if (map==null){
                return CommonResult.error().message("支付失败");
            }
            if (map.get("trade_state").equals("SUCCESS")){
                payLogService.updateOrdersStatus(map);
                return CommonResult.ok().message("支付成功");
            }
        return CommonResult.ok().code(25000).message("支付中");
    }

}


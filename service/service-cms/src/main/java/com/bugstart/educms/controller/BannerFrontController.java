package com.bugstart.educms.controller;

import com.bugstart.educms.entity.CrmBanner;
import com.bugstart.educms.service.CrmBannerService;
import com.bugstart.utils.CommonResult;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author bugstart
 * @create 2023-02-01 15:42
 */
@RestController
@CrossOrigin
@RequestMapping("/educms/bannerfront")
public class BannerFrontController {

    @Resource
    private CrmBannerService bannerService;

    @GetMapping("/getAllBanner")
    public CommonResult getAllBanner(){
      List<CrmBanner> list= bannerService.selectAllBanner();
      return CommonResult.ok().data("list",list);
    }
}

package com.bugstart.educms.controller;


import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.bugstart.educms.entity.CrmBanner;
import com.bugstart.educms.service.CrmBannerService;
import com.bugstart.utils.CommonResult;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * <p>
 * 首页banner表 前端控制器
 * </p>
 *
 * @author bugstart
 * @since 2023-02-01
 */
@RestController
@CrossOrigin
@RequestMapping("/educms/banneradmin")
public class BannerAdminController {

    @Resource
    private CrmBannerService bannerService;

    /**
     * 分页查询
     * @param page
     * @param limit
     * @return
     */
    @GetMapping("pageBanner/{page}/{limit}")
    public CommonResult pageBanner(@PathVariable("page") long page,@PathVariable("limit") long limit){
        Page<CrmBanner> pages = new Page<>(page,limit);
        bannerService.page(pages,null);
        return CommonResult.ok().data("items",pages.getRecords()).data("total",pages.getTotal());
    }

    /**
     * 添加banner
     * @param crmBanner
     * @return
     */
    @PostMapping("/addBanner")
    public CommonResult addBanner(@RequestBody CrmBanner crmBanner){
        bannerService.save(crmBanner);
        return CommonResult.ok();
    }

    @GetMapping("/get/{id}")
    public CommonResult get(@PathVariable("id") String id ){
        CrmBanner banner = bannerService.getById(id);
        return CommonResult.ok().data("item",banner);
    }
    @PutMapping("/update")
    public CommonResult updateById(@RequestBody CrmBanner banner){
        bannerService.updateById(banner);
        return CommonResult.ok();
    }
    @DeleteMapping("/remove/{id}")
    public CommonResult remove(@PathVariable String id){
        bannerService.removeById(id);
        return CommonResult.ok();
    }
}


package com.tencent.oa.educms.controller;


import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.tencent.oa.commonutils.JsonResult;
import com.tencent.oa.educms.entity.CrmBanner;
import com.tencent.oa.educms.service.CrmBannerService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * <p>
 * 首页banner表 后台控制器
 * </p>
 *
 * @author EvanMoz
 * @since 2021-02-23
 */
@RestController
@RequestMapping("/educms/banneradmin")
@CrossOrigin
public class BannerAdminController {
    @Autowired
    private CrmBannerService bannerService;

    //分页查询banner
    @GetMapping("pageBanner/{page}/{limit}")
    public JsonResult pageBanner(@PathVariable long page, @PathVariable long limit) {
        Page<CrmBanner> bannerPage = new Page<>(page,limit);
        bannerService.page(bannerPage,null);
        return JsonResult.success().data("items",bannerPage.getRecords()).data("total",bannerPage.getTotal());
    }

    //查询banner
    @ApiOperation(value = "获取Banner")
    @GetMapping("get/{id}")
    public JsonResult get(@PathVariable String id) {
        CrmBanner banner = bannerService.getById(id);
        return JsonResult.success().data("item", banner);
    }

    //添加banner
    @PostMapping("addBanner")
    public JsonResult addBanner(@RequestBody CrmBanner crmBanner){
        bannerService.save(crmBanner);
        return JsonResult.success();
    }

    //
    @ApiOperation(value = "修改Banner")
    @PutMapping("update")
    public JsonResult updateById(@RequestBody CrmBanner banner) {
        bannerService.updateById(banner);
        return JsonResult.success();
    }

    //
    @ApiOperation(value = "删除Banner")
    @DeleteMapping("remove/{id}")
    public JsonResult remove(@PathVariable String id) {
        bannerService.removeById(id);
        return JsonResult.success();
    }
}


package com.tencent.oa.educms.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.tencent.oa.commonutils.JsonResult;
import com.tencent.oa.educms.entity.CrmBanner;
import com.tencent.oa.educms.service.CrmBannerService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * <p>
 * 首页banner表 前端控制器
 * </p>
 *
 * @author EvanMoz
 * @since 2021-02-23
 */
@RestController
@RequestMapping("/educms/bannerfront")
@CrossOrigin
public class BannerFrontController {

    @Autowired
    private CrmBannerService bannerService;

    @GetMapping("getAllBanner")
    private JsonResult getAllBanner(){
        List<CrmBanner> list = bannerService.selectAllBanner();
        return JsonResult.success().data("list",list);
    }
}

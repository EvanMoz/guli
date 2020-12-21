package com.tencent.oa.eduservice.controller;

import com.tencent.oa.commonutils.JsonResult;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/eduservice/user")
@CrossOrigin
public class EduLoginController {


    @PostMapping("login")
    public JsonResult login(){
        return JsonResult.success().data("token","admin");
    }

    @GetMapping("info")
    public JsonResult getInfo(){
        return JsonResult.success().data("roles","[admin]").data("name","admin").data("avatar","https://wpimg.wallstcn.com/f778738c-e4f8-4870-b634-56703b4acafe.gif");
    }

}

package com.tencent.oa.eduservice.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.tencent.oa.commonutils.JsonResult;
import com.tencent.oa.eduservice.entity.EduTeacher;
import com.tencent.oa.eduservice.entity.vo.TeacherQuery;
import com.tencent.oa.eduservice.service.EduTeacherService;
import com.tencent.oa.servicebase.exception.GuliException;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import java.util.List;


/**
 * <p>
 * 讲师 前端控制器
 * </p>
 *
 * @author testjava
 * @since 2020-12-14
 */
@Api(description = "讲师管理")
@RestController
@RequestMapping("/eduservice/eduteacher")
@CrossOrigin
public class EduTeacherController {

    //把service注入
    @Autowired
    public EduTeacherService eduTeacherService;

    //查询讲师表所有数据
    @ApiOperation(value = "查询所有讲师")
    @GetMapping("findAll")
    public JsonResult findAllTeacher(){
        List<EduTeacher> list = eduTeacherService.list(null);
        return JsonResult.success().data("teachers",list);
    }

    //删除
    @ApiOperation(value = "根据ID逻辑删除讲师")
    @DeleteMapping("/delete/{id}")
    public JsonResult deleteTeacherById(@ApiParam(name = "id",value = "讲师ID",required = true) @PathVariable String id){
        boolean flag = eduTeacherService.removeById(id);
        return JsonResult.success().flag(flag);
    }

    @ApiOperation(value = "分页查询所有讲师")
    @GetMapping(value = "pageTeacher/{current}/{limit}")
    public JsonResult queryTeacherByPage(@PathVariable long current,
                                         @PathVariable long limit){
        Page<EduTeacher> pageTeacher = new Page<>(current,limit);

        eduTeacherService.page(pageTeacher,null);

        List<EduTeacher> records = pageTeacher.getRecords();
        long total = pageTeacher.getTotal();
        return JsonResult.success().data("total",total).data("items",records);
    }

    @ApiOperation("多条件组合分页查询讲师")
    @PostMapping(value = "pageTeacherCondition/{current}/{limit}")
    public JsonResult queryTercherConditionByPage(@PathVariable long current,
                                                  @PathVariable long limit,
                                                  @RequestBody(required = false) TeacherQuery teacherQuery){
        Page<EduTeacher> pageTeacher = new Page<>(current,limit);
        QueryWrapper<EduTeacher> queryWrapper = new QueryWrapper<>();
        String name = teacherQuery.getName();
        Integer level = teacherQuery.getLevel();
        String begin = teacherQuery.getBegin();
        String end = teacherQuery.getEnd();

        if(!StringUtils.isEmpty(name)){
            queryWrapper.like("name",name);
        }
        if (!StringUtils.isEmpty(level)){
            queryWrapper.eq("level",level);
        }
        if (!StringUtils.isEmpty(begin)){
            queryWrapper.ge("gmt_create",begin);
        }
        if (!StringUtils.isEmpty(end)){
            queryWrapper.le("gmt_create",end);
        }

        eduTeacherService.page(pageTeacher,queryWrapper);
        List<EduTeacher> records = pageTeacher.getRecords();
        long total = pageTeacher.getTotal();
        return JsonResult.success().data("total",total).data("items",records);
    }

    //添加讲师
    @ApiOperation("添加讲师")
    @PostMapping(value = "addTeacher")
    public JsonResult addTeacher(@RequestBody EduTeacher eduTeacher){
        boolean flag = eduTeacherService.save(eduTeacher);
        if (flag){
            return JsonResult.success().message("添加成功");
        }else {
            return JsonResult.failed().message("添加失败");
        }
    }

    //根据讲师ID进行查询
    @GetMapping(value = "getTeacher/{id}")
    public JsonResult getTeacherById(@PathVariable String id){
        EduTeacher eduTeacher = eduTeacherService.getById(id);
        return JsonResult.success().data("teacher",eduTeacher);
    }

    @PostMapping(value = "updateTeacher")
    public JsonResult updateTeacher(@RequestBody EduTeacher eduTeacher){
        boolean flag = eduTeacherService.updateById(eduTeacher);
        if (flag){
            return JsonResult.success().message("修改成功");
        }else{
            return JsonResult.failed().message("修改失败");
        }
    }






}


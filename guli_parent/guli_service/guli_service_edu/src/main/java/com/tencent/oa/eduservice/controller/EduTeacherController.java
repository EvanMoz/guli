package com.tencent.oa.eduservice.controller;


import com.tencent.oa.eduservice.entity.EduTeacher;
import com.tencent.oa.eduservice.service.EduTeacherService;
import org.springframework.beans.factory.annotation.Autowired;
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
@RestController
@RequestMapping("/eduservice/eduteacher")
public class EduTeacherController {

    //把service注入
    @Autowired
    public EduTeacherService eduTeacherService;

    //查询讲师表所有数据
    @GetMapping("findAll")
    public List<EduTeacher> findAll(){
        List<EduTeacher> list = eduTeacherService.list(null);
        return list;
    }

    //删除
    @DeleteMapping("/delete/{id}")
    public boolean deleteTeacherById(@PathVariable Long id){
        boolean flag = eduTeacherService.removeById(id);
        return flag;
    }
}


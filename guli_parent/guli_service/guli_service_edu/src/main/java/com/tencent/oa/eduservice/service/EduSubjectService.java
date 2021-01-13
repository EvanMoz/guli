package com.tencent.oa.eduservice.service;

import com.tencent.oa.eduservice.entity.EduSubject;
import com.baomidou.mybatisplus.extension.service.IService;
import com.tencent.oa.eduservice.entity.subject.OneSubject;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/**
 * <p>
 * 课程科目 服务类
 * </p>
 *
 * @author EvanMoz
 * @since 2021-01-08
 */
public interface EduSubjectService extends IService<EduSubject> {
    //添加课程分类
    void saveSubject(MultipartFile file,EduSubjectService eduSubjectService);

    List<OneSubject> getAllOneTwoSubject();
}

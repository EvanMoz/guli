package com.tencent.oa.eduservice.service.impl;

import com.alibaba.excel.EasyExcel;
import com.tencent.oa.eduservice.entity.EduSubject;
import com.tencent.oa.eduservice.entity.excel.ExcelSubjectData;
import com.tencent.oa.eduservice.listener.SubjectExcelListener;
import com.tencent.oa.eduservice.mapper.EduSubjectMapper;
import com.tencent.oa.eduservice.service.EduSubjectService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.InputStream;

/**
 * <p>
 * 课程科目 服务实现类
 * </p>
 *
 * @author EvanMoz
 * @since 2021-01-08
 */
@Service
public class EduSubjectServiceImpl extends ServiceImpl<EduSubjectMapper, EduSubject> implements EduSubjectService {

    //添加课程分类
    @Override
    public void saveSubject(MultipartFile file,EduSubjectService eduSubjectService) {
        try{
            InputStream inputStream = file.getInputStream();
            EasyExcel.read(inputStream, ExcelSubjectData.class,new SubjectExcelListener(eduSubjectService)).sheet().doRead();
        }
        catch (Exception e){
            e.printStackTrace();
        }

    }
}

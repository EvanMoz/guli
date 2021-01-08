package com.tencent.oa.eduservice.listener;

import com.alibaba.excel.context.AnalysisContext;
import com.alibaba.excel.event.AnalysisEventListener;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.tencent.oa.eduservice.entity.EduSubject;
import com.tencent.oa.eduservice.entity.excel.ExcelSubjectData;
import com.tencent.oa.eduservice.service.EduSubjectService;
import com.tencent.oa.servicebase.exception.GuliException;

public class SubjectExcelListener extends AnalysisEventListener<ExcelSubjectData> {

    public EduSubjectService subjectService;

    public SubjectExcelListener() {
    }

    public SubjectExcelListener(EduSubjectService subjectService) {
        this.subjectService = subjectService;
    }

    @Override
    public void invoke(ExcelSubjectData excelSubjectData, AnalysisContext analysisContext) {
        if (excelSubjectData == null){
            throw new GuliException(20001,"文件数据为空");
        }
        EduSubject existOneSubject = this.existOneSubject(subjectService, excelSubjectData.getOneSubjectName());
        if (existOneSubject == null){
            existOneSubject = new EduSubject();
            existOneSubject.setParentId("0");
            existOneSubject.setTitle(excelSubjectData.getOneSubjectName());
            subjectService.save(existOneSubject);
        }

        String pid = existOneSubject.getId();
        EduSubject existTwoSubject = this.existTwoSubject(subjectService, excelSubjectData.getTwoSubjectName(), pid);
        if (existTwoSubject == null){
            existTwoSubject = new EduSubject();
            existTwoSubject.setParentId(pid);
            existTwoSubject.setTitle(excelSubjectData.getTwoSubjectName());
            subjectService.save(existTwoSubject);
        }

    }

    //判断一级分类不能重复
    private EduSubject existOneSubject(EduSubjectService subjectService,String name){
        QueryWrapper<EduSubject> wrapper = new QueryWrapper<>();
        wrapper.eq("title",name);
        wrapper.eq("parent_id","0");
        EduSubject oneSubject = subjectService.getOne(wrapper);
        return oneSubject;
    }

    //判断二级分类不能重复
    private EduSubject existTwoSubject(EduSubjectService subjectService,String name,String pid){
        QueryWrapper<EduSubject> wrapper = new QueryWrapper<>();
        wrapper.eq("title",name);
        wrapper.eq("parent_id",pid);
        EduSubject twoSubject = subjectService.getOne(wrapper);
        return twoSubject;
    }

    @Override
    public void doAfterAllAnalysed(AnalysisContext analysisContext) {

    }
}

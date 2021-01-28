package com.tencent.oa.eduservice.service;

import com.tencent.oa.eduservice.chapter.ChapterVo;
import com.tencent.oa.eduservice.entity.EduChapter;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 * 课程 服务类
 * </p>
 *
 * @author EvanMoz
 * @since 2021-01-11
 */
public interface EduChapterService extends IService<EduChapter> {

    List<ChapterVo> getChapterVideoByCourseId(String courseId);

    boolean deleteChapter(String chapterId);

    void deleteChapterByCourseId(String courseId);
}

package com.northbrain.product.course.dao;

import org.apache.ibatis.annotations.Mapper;

import com.northbrain.product.course.model.po.LessonDetailPO;

@Mapper
public interface LessonDetailPOMapper 
{
    int deleteByPrimaryKey(Integer lessonDetailId);

    int insert(LessonDetailPO record);

    int insertSelective(LessonDetailPO record);

    LessonDetailPO selectByPrimaryKey(Integer lessonDetailId);

    int updateByPrimaryKeySelective(LessonDetailPO record);

    int updateByPrimaryKey(LessonDetailPO record);
}
package com.northbrain.product.course.dao;

import org.apache.ibatis.annotations.Mapper;

import com.northbrain.product.course.model.po.LessonPO;

@Mapper
public interface LessonPOMapper 
{
    int deleteByPrimaryKey(Integer lessonId);

    int insert(LessonPO record);

    int insertSelective(LessonPO record);

    LessonPO selectByPrimaryKey(Integer lessonId);

    int updateByPrimaryKeySelective(LessonPO record);

    int updateByPrimaryKey(LessonPO record);
}
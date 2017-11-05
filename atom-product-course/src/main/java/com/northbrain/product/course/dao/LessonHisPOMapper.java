package com.northbrain.product.course.dao;

import org.apache.ibatis.annotations.Mapper;

import com.northbrain.product.course.model.po.LessonHisPO;

@Mapper
public interface LessonHisPOMapper 
{
    int deleteByPrimaryKey(Integer recordId);

    int insert(LessonHisPO record);

    int insertSelective(LessonHisPO record);

    LessonHisPO selectByPrimaryKey(Integer recordId);

    int updateByPrimaryKeySelective(LessonHisPO record);

    int updateByPrimaryKey(LessonHisPO record);
}
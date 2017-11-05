package com.northbrain.product.course.dao;

import org.apache.ibatis.annotations.Mapper;

import com.northbrain.product.course.model.po.LessonDetailHisPO;

@Mapper
public interface LessonDetailHisPOMapper 
{
    int deleteByPrimaryKey(Integer recordId);

    int insert(LessonDetailHisPO record);

    int insertSelective(LessonDetailHisPO record);

    LessonDetailHisPO selectByPrimaryKey(Integer recordId);

    int updateByPrimaryKeySelective(LessonDetailHisPO record);

    int updateByPrimaryKey(LessonDetailHisPO record);
}
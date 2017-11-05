package com.northbrain.product.course.dao;

import org.apache.ibatis.annotations.Mapper;

import com.northbrain.product.course.model.po.CourseHisPO;

@Mapper
public interface CourseHisPOMapper 
{
    int deleteByPrimaryKey(Integer recordId);

    int insert(CourseHisPO record);

    int insertSelective(CourseHisPO record);

    CourseHisPO selectByPrimaryKey(Integer recordId);

    int updateByPrimaryKeySelective(CourseHisPO record);

    int updateByPrimaryKey(CourseHisPO record);
}
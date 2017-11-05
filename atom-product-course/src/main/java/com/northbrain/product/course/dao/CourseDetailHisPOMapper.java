package com.northbrain.product.course.dao;

import org.apache.ibatis.annotations.Mapper;

import com.northbrain.product.course.model.po.CourseDetailHisPO;

@Mapper
public interface CourseDetailHisPOMapper 
{
    int deleteByPrimaryKey(Integer recordId);

    int insert(CourseDetailHisPO record);

    int insertSelective(CourseDetailHisPO record);

    CourseDetailHisPO selectByPrimaryKey(Integer recordId);

    int updateByPrimaryKeySelective(CourseDetailHisPO record);

    int updateByPrimaryKey(CourseDetailHisPO record);
}
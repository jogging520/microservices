package com.northbrain.product.course.dao;

import org.apache.ibatis.annotations.Mapper;

import com.northbrain.product.course.model.po.CourseRatingHisPO;

@Mapper
public interface CourseRatingHisPOMapper 
{
    int deleteByPrimaryKey(Integer recordId);

    int insert(CourseRatingHisPO record);

    int insertSelective(CourseRatingHisPO record);

    CourseRatingHisPO selectByPrimaryKey(Integer recordId);

    int updateByPrimaryKeySelective(CourseRatingHisPO record);

    int updateByPrimaryKey(CourseRatingHisPO record);
}
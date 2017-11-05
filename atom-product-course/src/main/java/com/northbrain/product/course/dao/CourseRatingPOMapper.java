package com.northbrain.product.course.dao;

import org.apache.ibatis.annotations.Mapper;

import com.northbrain.product.course.model.po.CourseRatingPO;

@Mapper
public interface CourseRatingPOMapper 
{
    int deleteByPrimaryKey(Integer ratingId);

    int insert(CourseRatingPO record);

    int insertSelective(CourseRatingPO record);

    CourseRatingPO selectByPrimaryKey(Integer ratingId);

    int updateByPrimaryKeySelective(CourseRatingPO record);

    int updateByPrimaryKey(CourseRatingPO record);
}
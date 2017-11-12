package com.northbrain.product.course.dao;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Component;

import com.northbrain.product.course.model.po.CourseRatingPO;

@Mapper
@Component(value="courseRatingPOMapper")
public interface CourseRatingPOMapper 
{
    int deleteByPrimaryKey(Integer ratingId) throws Exception;

    int insert(CourseRatingPO record) throws Exception;

    int insertSelective(CourseRatingPO record) throws Exception;

    CourseRatingPO selectByPrimaryKey(Integer ratingId) throws Exception;

    int updateByPrimaryKeySelective(CourseRatingPO record) throws Exception;

    int updateByPrimaryKey(CourseRatingPO record) throws Exception;
}
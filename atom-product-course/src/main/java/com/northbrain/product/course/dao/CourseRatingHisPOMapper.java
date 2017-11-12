package com.northbrain.product.course.dao;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Component;

import com.northbrain.product.course.model.po.CourseRatingHisPO;

@Mapper
@Component(value="courseRatingHisPOMapper")
public interface CourseRatingHisPOMapper 
{
    int deleteByPrimaryKey(Integer recordId) throws Exception;

    int insert(CourseRatingHisPO record) throws Exception;

    int insertSelective(CourseRatingHisPO record) throws Exception;

    CourseRatingHisPO selectByPrimaryKey(Integer recordId) throws Exception;

    int updateByPrimaryKeySelective(CourseRatingHisPO record) throws Exception;

    int updateByPrimaryKey(CourseRatingHisPO record) throws Exception;
}
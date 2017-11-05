package com.northbrain.product.course.dao;

import org.apache.ibatis.annotations.Mapper;

import com.northbrain.product.course.model.po.CourseCommentHisPO;

@Mapper
public interface CourseCommentHisPOMapper 
{
    int deleteByPrimaryKey(Integer recordId);

    int insert(CourseCommentHisPO record);

    int insertSelective(CourseCommentHisPO record);

    CourseCommentHisPO selectByPrimaryKey(Integer recordId);

    int updateByPrimaryKeySelective(CourseCommentHisPO record);

    int updateByPrimaryKey(CourseCommentHisPO record);
}
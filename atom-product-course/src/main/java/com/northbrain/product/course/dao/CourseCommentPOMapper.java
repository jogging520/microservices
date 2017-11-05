package com.northbrain.product.course.dao;

import org.apache.ibatis.annotations.Mapper;

import com.northbrain.product.course.model.po.CourseCommentPO;

@Mapper
public interface CourseCommentPOMapper 
{
    int deleteByPrimaryKey(Integer commentId);

    int insert(CourseCommentPO record);

    int insertSelective(CourseCommentPO record);

    CourseCommentPO selectByPrimaryKey(Integer commentId);

    int updateByPrimaryKeySelective(CourseCommentPO record);

    int updateByPrimaryKey(CourseCommentPO record);
}
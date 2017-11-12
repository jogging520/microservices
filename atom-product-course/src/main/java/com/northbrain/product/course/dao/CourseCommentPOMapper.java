package com.northbrain.product.course.dao;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Component;

import com.northbrain.product.course.model.po.CourseCommentPO;

@Mapper
@Component(value="courseCommentPOMapper")
public interface CourseCommentPOMapper 
{
    int deleteByPrimaryKey(Integer commentId) throws Exception;

    int insert(CourseCommentPO record) throws Exception;

    int insertSelective(CourseCommentPO record) throws Exception;

    CourseCommentPO selectByPrimaryKey(Integer commentId) throws Exception;

    int updateByPrimaryKeySelective(CourseCommentPO record) throws Exception;

    int updateByPrimaryKey(CourseCommentPO record) throws Exception;
}
package com.northbrain.product.course.dao;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Component;

import com.northbrain.product.course.model.po.CourseCommentHisPO;

@Mapper
@Component(value="courseCommentHisPOMapper")
public interface CourseCommentHisPOMapper 
{
    int deleteByPrimaryKey(Integer recordId) throws Exception;

    int insert(CourseCommentHisPO record) throws Exception;

    int insertSelective(CourseCommentHisPO record) throws Exception;

    CourseCommentHisPO selectByPrimaryKey(Integer recordId) throws Exception;

    int updateByPrimaryKeySelective(CourseCommentHisPO record) throws Exception;

    int updateByPrimaryKey(CourseCommentHisPO record) throws Exception;
}
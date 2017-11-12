package com.northbrain.product.course.dao;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Component;

import com.northbrain.product.course.model.po.LessonHisPO;

@Mapper
@Component(value="lessonHisPOMapper")
public interface LessonHisPOMapper 
{
    int deleteByPrimaryKey(Integer recordId) throws Exception;

    int insert(LessonHisPO record) throws Exception;

    int insertSelective(LessonHisPO record) throws Exception;

    LessonHisPO selectByPrimaryKey(Integer recordId) throws Exception;

    int updateByPrimaryKeySelective(LessonHisPO record) throws Exception;

    int updateByPrimaryKey(LessonHisPO record) throws Exception;
}
package com.northbrain.product.course.dao;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Component;

import com.northbrain.product.course.model.po.LessonPO;

@Mapper
@Component(value="lessonPOMapper")
public interface LessonPOMapper 
{
    int deleteByPrimaryKey(Integer lessonId) throws Exception;

    int insert(LessonPO record) throws Exception;

    int insertSelective(LessonPO record) throws Exception;

    LessonPO selectByPrimaryKey(Integer lessonId) throws Exception;

    int updateByPrimaryKeySelective(LessonPO record) throws Exception;

    int updateByPrimaryKey(LessonPO record) throws Exception;
}
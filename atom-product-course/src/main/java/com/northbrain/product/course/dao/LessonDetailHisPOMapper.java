package com.northbrain.product.course.dao;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Component;

import com.northbrain.product.course.model.po.LessonDetailHisPO;

@Mapper
@Component(value="lessonDetailHisPOMapper")
public interface LessonDetailHisPOMapper 
{
    int deleteByPrimaryKey(Integer recordId) throws Exception;

    int insert(LessonDetailHisPO record) throws Exception;

    int insertSelective(LessonDetailHisPO record) throws Exception;

    LessonDetailHisPO selectByPrimaryKey(Integer recordId) throws Exception;

    int updateByPrimaryKeySelective(LessonDetailHisPO record) throws Exception;

    int updateByPrimaryKey(LessonDetailHisPO record) throws Exception;
}
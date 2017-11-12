package com.northbrain.product.course.dao;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Component;

import com.northbrain.product.course.model.po.LessonDetailPO;

@Mapper
@Component(value="lessonDetailPOMapper")
public interface LessonDetailPOMapper 
{
    int deleteByPrimaryKey(Integer lessonDetailId) throws Exception;

    int insert(LessonDetailPO record) throws Exception;

    int insertSelective(LessonDetailPO record) throws Exception;

    LessonDetailPO selectByPrimaryKey(Integer lessonDetailId) throws Exception;

    int updateByPrimaryKeySelective(LessonDetailPO record) throws Exception;

    int updateByPrimaryKey(LessonDetailPO record) throws Exception;
}
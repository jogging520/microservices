package com.northbrain.product.course.dao;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Component;

import com.northbrain.product.course.model.po.CourseHisPO;

@Mapper
@Component(value="courseHisPOMapper")
public interface CourseHisPOMapper
{
    int deleteByPrimaryKey(Integer recordId) throws Exception;

    int insert(CourseHisPO record) throws Exception;

    int insertSelective(CourseHisPO record) throws Exception;

    CourseHisPO selectByPrimaryKey(Integer recordId) throws Exception;

    int updateByPrimaryKeySelective(CourseHisPO record) throws Exception;

    int updateByPrimaryKey(CourseHisPO record) throws Exception;
}
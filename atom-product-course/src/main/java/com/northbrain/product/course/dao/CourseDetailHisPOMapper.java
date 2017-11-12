package com.northbrain.product.course.dao;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Component;

import com.northbrain.product.course.model.po.CourseDetailHisPO;

@Mapper
@Component(value="courseDetailHisPOMapper")
public interface CourseDetailHisPOMapper 
{
    int deleteByPrimaryKey(Integer recordId) throws Exception;

    int insert(CourseDetailHisPO record) throws Exception;

    int insertSelective(CourseDetailHisPO record) throws Exception;

    CourseDetailHisPO selectByPrimaryKey(Integer recordId) throws Exception;

    int updateByPrimaryKeySelective(CourseDetailHisPO record) throws Exception;

    int updateByPrimaryKey(CourseDetailHisPO record) throws Exception;
}
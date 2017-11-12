package com.northbrain.product.course.dao;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Component;

import com.northbrain.product.course.model.po.CourseDetailPO;

@Mapper
@Component(value="courseDetailPOMapper")
public interface CourseDetailPOMapper 
{
    int deleteByPrimaryKey(Integer courseDetailId) throws Exception;

    int insert(CourseDetailPO record) throws Exception;

    int insertSelective(CourseDetailPO record) throws Exception;

    CourseDetailPO selectByPrimaryKey(Integer courseDetailId) throws Exception;

    int updateByPrimaryKeySelective(CourseDetailPO record) throws Exception;

    int updateByPrimaryKey(CourseDetailPO record) throws Exception;
}
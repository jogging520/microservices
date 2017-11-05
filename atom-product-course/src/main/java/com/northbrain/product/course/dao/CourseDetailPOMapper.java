package com.northbrain.product.course.dao;

import org.apache.ibatis.annotations.Mapper;

import com.northbrain.product.course.model.po.CourseDetailPO;

@Mapper
public interface CourseDetailPOMapper 
{
    int deleteByPrimaryKey(Integer courseDetailId);

    int insert(CourseDetailPO record);

    int insertSelective(CourseDetailPO record);

    CourseDetailPO selectByPrimaryKey(Integer courseDetailId);

    int updateByPrimaryKeySelective(CourseDetailPO record);

    int updateByPrimaryKey(CourseDetailPO record);
}
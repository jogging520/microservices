package com.northbrain.product.course.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.northbrain.product.course.model.po.CoursePO;

@Mapper
public interface CoursePOMapper 
{
    int deleteByPrimaryKey(Integer courseId);

    int insert(CoursePO record);

    int insertSelective(CoursePO record);

    CoursePO selectByPrimaryKey(Integer courseId);
    
    List<CoursePO> selectByStatus(@Param("status") Integer status);

    int updateByPrimaryKeySelective(CoursePO record);

    int updateByPrimaryKey(CoursePO record);
}
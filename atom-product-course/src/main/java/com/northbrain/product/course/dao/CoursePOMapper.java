package com.northbrain.product.course.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

import com.northbrain.product.course.model.po.CoursePO;

@Mapper
@Component(value="coursePOMapper")
public interface CoursePOMapper
{
    int deleteByPrimaryKey(Integer courseId) throws Exception;

    int insert(CoursePO record) throws Exception;

    int insertSelective(CoursePO record) throws Exception;

    CoursePO selectByPrimaryKey(Integer courseId) throws Exception;

    List<CoursePO> selectByStatus(@Param("status") Integer status) throws Exception;

    int updateByPrimaryKeySelective(CoursePO record) throws Exception;

    int updateByPrimaryKey(CoursePO record) throws Exception;
}
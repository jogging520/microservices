package com.northbrain.product.course.dao;

import org.apache.ibatis.annotations.Mapper;

import com.northbrain.product.course.model.po.ChapterHisPO;

@Mapper
public interface ChapterHisPOMapper 
{
    int deleteByPrimaryKey(Integer recordId);

    int insert(ChapterHisPO record);

    int insertSelective(ChapterHisPO record);

    ChapterHisPO selectByPrimaryKey(Integer recordId);

    int updateByPrimaryKeySelective(ChapterHisPO record);

    int updateByPrimaryKey(ChapterHisPO record);
}
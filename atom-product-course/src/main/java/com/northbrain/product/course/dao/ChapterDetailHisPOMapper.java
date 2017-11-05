package com.northbrain.product.course.dao;

import org.apache.ibatis.annotations.Mapper;

import com.northbrain.product.course.model.po.ChapterDetailHisPO;

@Mapper
public interface ChapterDetailHisPOMapper 
{
    int deleteByPrimaryKey(Integer recordId);

    int insert(ChapterDetailHisPO record);

    int insertSelective(ChapterDetailHisPO record);

    ChapterDetailHisPO selectByPrimaryKey(Integer recordId);

    int updateByPrimaryKeySelective(ChapterDetailHisPO record);

    int updateByPrimaryKey(ChapterDetailHisPO record);
}
package com.northbrain.product.course.dao;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Component;

import com.northbrain.product.course.model.po.ChapterDetailHisPO;

@Mapper
@Component(value="chapterDetailHisPOMapper")
public interface ChapterDetailHisPOMapper 
{
    int deleteByPrimaryKey(Integer recordId) throws Exception;

    int insert(ChapterDetailHisPO record) throws Exception;

    int insertSelective(ChapterDetailHisPO record) throws Exception;

    ChapterDetailHisPO selectByPrimaryKey(Integer recordId) throws Exception;

    int updateByPrimaryKeySelective(ChapterDetailHisPO record) throws Exception;

    int updateByPrimaryKey(ChapterDetailHisPO record) throws Exception;
}
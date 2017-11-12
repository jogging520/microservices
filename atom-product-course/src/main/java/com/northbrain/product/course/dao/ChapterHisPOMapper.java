package com.northbrain.product.course.dao;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Component;

import com.northbrain.product.course.model.po.ChapterHisPO;

@Mapper
@Component(value="chapterHisPOMapper")
public interface ChapterHisPOMapper 
{
    int deleteByPrimaryKey(Integer recordId) throws Exception;

    int insert(ChapterHisPO record) throws Exception;

    int insertSelective(ChapterHisPO record) throws Exception;

    ChapterHisPO selectByPrimaryKey(Integer recordId) throws Exception;

    int updateByPrimaryKeySelective(ChapterHisPO record) throws Exception;

    int updateByPrimaryKey(ChapterHisPO record) throws Exception;
}
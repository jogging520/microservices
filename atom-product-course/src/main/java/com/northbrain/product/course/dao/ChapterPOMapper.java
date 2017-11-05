package com.northbrain.product.course.dao;

import org.apache.ibatis.annotations.Mapper;

import com.northbrain.product.course.model.po.ChapterPO;

@Mapper
public interface ChapterPOMapper 
{
    int deleteByPrimaryKey(Integer chapterId);

    int insert(ChapterPO record);

    int insertSelective(ChapterPO record);

    ChapterPO selectByPrimaryKey(Integer chapterId);

    int updateByPrimaryKeySelective(ChapterPO record);

    int updateByPrimaryKey(ChapterPO record);
}
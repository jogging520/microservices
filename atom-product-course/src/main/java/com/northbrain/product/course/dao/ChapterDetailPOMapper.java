package com.northbrain.product.course.dao;

import org.apache.ibatis.annotations.Mapper;

import com.northbrain.product.course.model.po.ChapterDetailPO;

@Mapper
public interface ChapterDetailPOMapper 
{
    int deleteByPrimaryKey(Integer chapterDetailId);

    int insert(ChapterDetailPO record);

    int insertSelective(ChapterDetailPO record);

    ChapterDetailPO selectByPrimaryKey(Integer chapterDetailId);

    int updateByPrimaryKeySelective(ChapterDetailPO record);

    int updateByPrimaryKey(ChapterDetailPO record);
}
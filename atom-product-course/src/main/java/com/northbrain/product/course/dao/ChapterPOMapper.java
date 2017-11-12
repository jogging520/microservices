package com.northbrain.product.course.dao;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Component;

import com.northbrain.product.course.model.po.ChapterPO;

@Mapper
@Component(value="chapterPOMapper")
public interface ChapterPOMapper 
{
    int deleteByPrimaryKey(Integer chapterId) throws Exception;

    int insert(ChapterPO record) throws Exception;

    int insertSelective(ChapterPO record) throws Exception;

    ChapterPO selectByPrimaryKey(Integer chapterId) throws Exception;

    int updateByPrimaryKeySelective(ChapterPO record) throws Exception;

    int updateByPrimaryKey(ChapterPO record) throws Exception;
}
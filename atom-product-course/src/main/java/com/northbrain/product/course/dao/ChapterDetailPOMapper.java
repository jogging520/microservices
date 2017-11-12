package com.northbrain.product.course.dao;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Component;

import com.northbrain.product.course.model.po.ChapterDetailPO;

@Mapper
@Component(value="chapterDetailPOMapper")
public interface ChapterDetailPOMapper 
{
    int deleteByPrimaryKey(Integer chapterDetailId) throws Exception;

    int insert(ChapterDetailPO record) throws Exception;

    int insertSelective(ChapterDetailPO record) throws Exception;

    ChapterDetailPO selectByPrimaryKey(Integer chapterDetailId) throws Exception;

    int updateByPrimaryKeySelective(ChapterDetailPO record) throws Exception;

    int updateByPrimaryKey(ChapterDetailPO record) throws Exception;
}
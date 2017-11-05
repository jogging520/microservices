package com.northbrain.resource.storage.dao;

import org.apache.ibatis.annotations.Mapper;

import com.northbrain.resource.storage.model.po.StorageHisPO;

@Mapper
public interface StorageHisPOMapper
{
    int deleteByPrimaryKey(Integer recordId);

    int insert(StorageHisPO record);

    int insertSelective(StorageHisPO record);

    StorageHisPO selectByPrimaryKey(Integer recordId);

    int updateByPrimaryKeySelective(StorageHisPO record);

    int updateByPrimaryKey(StorageHisPO record);
}
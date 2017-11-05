package com.northbrain.resource.storage.dao;

import org.apache.ibatis.annotations.Mapper;

import com.northbrain.resource.storage.model.po.StoragePO;

@Mapper
public interface StoragePOMapper
{
    int deleteByPrimaryKey(Integer storageId);

    int insert(StoragePO record);

    int insertSelective(StoragePO record);

    StoragePO selectByPrimaryKey(Integer storageId);

    int updateByPrimaryKeySelective(StoragePO record);

    int updateByPrimaryKey(StoragePO record);
}
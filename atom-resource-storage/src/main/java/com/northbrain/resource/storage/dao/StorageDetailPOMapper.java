package com.northbrain.resource.storage.dao;

import org.apache.ibatis.annotations.Mapper;

import com.northbrain.resource.storage.model.po.StorageDetailPO;

@Mapper
public interface StorageDetailPOMapper
{
    int deleteByPrimaryKey(Integer storageDetailId);

    int insert(StorageDetailPO record);

    int insertSelective(StorageDetailPO record);

    StorageDetailPO selectByPrimaryKey(Integer storageDetailId);

    int updateByPrimaryKeySelective(StorageDetailPO record);

    int updateByPrimaryKey(StorageDetailPO record);
}
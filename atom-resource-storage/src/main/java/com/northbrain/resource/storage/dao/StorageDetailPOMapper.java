package com.northbrain.resource.storage.dao;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Component;

import com.northbrain.resource.storage.model.po.StorageDetailPO;

@Mapper
@Component(value="storageDetailPOMapper")
public interface StorageDetailPOMapper
{
    int deleteByPrimaryKey(Integer storageDetailId) throws Exception;

    int insert(StorageDetailPO record) throws Exception;

    int insertSelective(StorageDetailPO record) throws Exception;

    StorageDetailPO selectByPrimaryKey(Integer storageDetailId) throws Exception;

    int updateByPrimaryKeySelective(StorageDetailPO record) throws Exception;

    int updateByPrimaryKey(StorageDetailPO record) throws Exception;
}
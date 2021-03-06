package com.northbrain.resource.storage.dao;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Component;

import com.northbrain.resource.storage.model.po.StoragePO;

@Mapper
@Component(value="storagePOMapper")
public interface StoragePOMapper 
{
    int deleteByPrimaryKey(Integer storageId) throws Exception;

    int insert(StoragePO record) throws Exception;

    int insertSelective(StoragePO record) throws Exception;

    StoragePO selectByPrimaryKey(Integer storageId) throws Exception;

    int updateByPrimaryKeySelective(StoragePO record) throws Exception;

    int updateByPrimaryKey(StoragePO record) throws Exception;
}
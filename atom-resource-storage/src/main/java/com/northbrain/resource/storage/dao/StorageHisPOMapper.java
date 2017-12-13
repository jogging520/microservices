package com.northbrain.resource.storage.dao;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Component;

import com.northbrain.resource.storage.model.po.StorageHisPO;

@Mapper
@Component(value="storageHisPOMapper")
public interface StorageHisPOMapper 
{
    int deleteByPrimaryKey(Integer recordId) throws Exception;

    int insert(StorageHisPO record) throws Exception;

    int insertSelective(StorageHisPO record) throws Exception;

    StorageHisPO selectByPrimaryKey(Integer recordId) throws Exception;

    int updateByPrimaryKeySelective(StorageHisPO record) throws Exception;

    int updateByPrimaryKey(StorageHisPO record) throws Exception;
}
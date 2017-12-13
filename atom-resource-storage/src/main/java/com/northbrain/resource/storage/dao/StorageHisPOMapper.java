package com.northbrain.resource.storage.dao;

import com.northbrain.resource.storage.model.po.StorageHisPO;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Component;

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
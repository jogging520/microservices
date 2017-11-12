package com.northbrain.resource.storage.dao;


import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Component;

import com.northbrain.resource.storage.model.po.StorageDetailHisPO;

@Mapper
@Component(value="storageDetailHisPOMapper")
public interface StorageDetailHisPOMapper
{
    int deleteByPrimaryKey(Integer recordId) throws Exception;

    int insert(StorageDetailHisPO record) throws Exception;

    int insertSelective(StorageDetailHisPO record) throws Exception;

    StorageDetailHisPO selectByPrimaryKey(Integer recordId) throws Exception;

    int updateByPrimaryKeySelective(StorageDetailHisPO record) throws Exception;

    int updateByPrimaryKey(StorageDetailHisPO record) throws Exception;
}
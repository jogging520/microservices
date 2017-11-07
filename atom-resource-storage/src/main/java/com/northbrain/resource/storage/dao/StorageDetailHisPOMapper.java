package com.northbrain.resource.storage.dao;


import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Component;

import com.northbrain.resource.storage.model.po.StorageDetailHisPO;

@Mapper
@Component(value="storageDetailHisPOMapper")
public interface StorageDetailHisPOMapper
{
    int deleteByPrimaryKey(Integer recordId);

    int insert(StorageDetailHisPO record);

    int insertSelective(StorageDetailHisPO record);

    StorageDetailHisPO selectByPrimaryKey(Integer recordId);

    int updateByPrimaryKeySelective(StorageDetailHisPO record);

    int updateByPrimaryKey(StorageDetailHisPO record);
}
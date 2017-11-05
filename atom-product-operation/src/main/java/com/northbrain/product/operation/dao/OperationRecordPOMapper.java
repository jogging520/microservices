package com.northbrain.product.operation.dao;

import com.northbrain.product.operation.model.po.OperationRecordPO;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface OperationRecordPOMapper
{
    int deleteByPrimaryKey(Integer recordId);

    int insert(OperationRecordPO record);

    int insertSelective(OperationRecordPO record);

    OperationRecordPO selectByPrimaryKey(Integer recordId);

    int updateByPrimaryKeySelective(OperationRecordPO record);

    int updateByPrimaryKey(OperationRecordPO record);
}
package com.northbrain.relation.operationrecord.dao;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Component;

import com.northbrain.relation.operationrecord.model.po.OperationRecordPO;

@Mapper
@Component(value="operationRecordPOMapper")
public interface OperationRecordPOMapper
{
    int deleteByPrimaryKey(Integer recordId) throws Exception;

    int insert(OperationRecordPO record) throws Exception;

    int insertSelective(OperationRecordPO record) throws Exception;

    OperationRecordPO selectByPrimaryKey(Integer recordId) throws Exception;

    int updateByPrimaryKeySelective(OperationRecordPO record) throws Exception;

    int updateByPrimaryKey(OperationRecordPO record) throws Exception;
}
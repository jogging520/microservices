package com.northbrain.relation.operationrecord.dao;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Component;

import com.northbrain.relation.operationrecord.model.po.OperationRecordDetailPO;

@Mapper
@Component(value="operationRecordDetailPOMapper")
public interface OperationRecordDetailPOMapper
{
    int deleteByPrimaryKey(Long recordDetailId) throws Exception;

    int insert(OperationRecordDetailPO record) throws Exception;

    int insertSelective(OperationRecordDetailPO record) throws Exception;

    OperationRecordDetailPO selectByPrimaryKey(Long recordDetailId) throws Exception;

    int updateByPrimaryKeySelective(OperationRecordDetailPO record) throws Exception;

    int updateByPrimaryKey(OperationRecordDetailPO record) throws Exception;
}
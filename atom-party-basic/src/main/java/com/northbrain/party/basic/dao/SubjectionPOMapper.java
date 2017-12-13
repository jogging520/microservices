package com.northbrain.party.basic.dao;

import com.northbrain.party.basic.model.po.SubjectionPO;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Component;

@Mapper
@Component(value="subjectionPOMapper")
public interface SubjectionPOMapper 
{
    int deleteByPrimaryKey(Integer subjectionId) throws Exception;

    int insert(SubjectionPO record) throws Exception;

    int insertSelective(SubjectionPO record) throws Exception;

    SubjectionPO selectByPrimaryKey(Integer subjectionId) throws Exception;

    int updateByPrimaryKeySelective(SubjectionPO record) throws Exception;

    int updateByPrimaryKey(SubjectionPO record) throws Exception;
}
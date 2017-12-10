package com.northbrain.party.organization.dao;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Component;

import com.northbrain.party.organization.model.po.SubjectionPO;

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
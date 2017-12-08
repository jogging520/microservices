package com.northbrain.party.role.dao;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Component;

import com.northbrain.party.role.model.po.PartyPO;

@Mapper
@Component(value="partyPOMapper")
public interface PartyPOMapper
{
    int deleteByPrimaryKey(Integer partyId) throws Exception;

    int insert(PartyPO record) throws Exception;

    int insertSelective(PartyPO record) throws Exception;

    PartyPO selectByPrimaryKey(Integer partyId) throws Exception;

    int updateByPrimaryKeySelective(PartyPO record) throws Exception;

    int updateByPrimaryKey(PartyPO record) throws Exception;
}
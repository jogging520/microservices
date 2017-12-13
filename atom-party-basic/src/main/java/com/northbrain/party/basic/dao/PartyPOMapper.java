package com.northbrain.party.basic.dao;

import com.northbrain.party.basic.model.po.PartyPO;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Component;

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
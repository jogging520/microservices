package com.northbrain.party.role.dao;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Component;

import com.northbrain.party.role.model.po.PartyPO;

@Mapper
@Component(value="partyPOMapper")
public interface PartyPOMapper {
    int deleteByPrimaryKey(Integer partyId);

    int insert(PartyPO record);

    int insertSelective(PartyPO record);

    PartyPO selectByPrimaryKey(Integer partyId);

    int updateByPrimaryKeySelective(PartyPO record);

    int updateByPrimaryKey(PartyPO record);
}
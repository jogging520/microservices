package com.northbrain.party.role.dao;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Component;

import com.northbrain.party.role.model.po.PartyHisPO;

@Mapper
@Component(value="partyHisPOMapper")
public interface PartyHisPOMapper {
    int deleteByPrimaryKey(Integer recordId);

    int insert(PartyHisPO record);

    int insertSelective(PartyHisPO record);

    PartyHisPO selectByPrimaryKey(Integer recordId);

    int updateByPrimaryKeySelective(PartyHisPO record);

    int updateByPrimaryKey(PartyHisPO record);
}
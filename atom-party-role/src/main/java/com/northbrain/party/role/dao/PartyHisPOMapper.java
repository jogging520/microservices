package com.northbrain.party.role.dao;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Component;

import com.northbrain.party.role.model.po.PartyHisPO;

@Mapper
@Component(value="partyHisPOMapper")
public interface PartyHisPOMapper
{
    int deleteByPrimaryKey(Integer recordId) throws Exception;

    int insert(PartyHisPO record) throws Exception;

    int insertSelective(PartyHisPO record) throws Exception;

    PartyHisPO selectByPrimaryKey(Integer recordId) throws Exception;

    int updateByPrimaryKeySelective(PartyHisPO record) throws Exception;

    int updateByPrimaryKey(PartyHisPO record) throws Exception;
}
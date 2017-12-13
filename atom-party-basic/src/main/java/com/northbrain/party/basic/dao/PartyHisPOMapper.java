package com.northbrain.party.basic.dao;

import com.northbrain.party.basic.model.po.PartyHisPO;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Component;

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
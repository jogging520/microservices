package com.northbrain.party.basic.dao;

import com.northbrain.party.basic.model.po.PartyDetailHisPO;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Component;

@Mapper
@Component(value="partyDetailHisPOMapper")
public interface PartyDetailHisPOMapper
{
    int deleteByPrimaryKey(Integer recordId) throws Exception;

    int insert(PartyDetailHisPO record) throws Exception;

    int insertSelective(PartyDetailHisPO record) throws Exception;

    PartyDetailHisPO selectByPrimaryKey(Integer recordId) throws Exception;

    int updateByPrimaryKeySelective(PartyDetailHisPO record) throws Exception;

    int updateByPrimaryKey(PartyDetailHisPO record) throws Exception;
}
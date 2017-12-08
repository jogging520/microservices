package com.northbrain.party.role.dao;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Component;

import com.northbrain.party.role.model.po.PartyDetailHisPO;

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
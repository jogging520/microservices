package com.northbrain.party.role.dao;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Component;

import com.northbrain.party.role.model.po.PartyDetailPO;

@Mapper
@Component(value="partyDetailPOMapper")
public interface PartyDetailPOMapper
{
    int deleteByPrimaryKey(Integer partyDetailId) throws Exception;

    int insert(PartyDetailPO record) throws Exception;

    int insertSelective(PartyDetailPO record) throws Exception;

    PartyDetailPO selectByPrimaryKey(Integer partyDetailId) throws Exception;

    int updateByPrimaryKeySelective(PartyDetailPO record) throws Exception;

    int updateByPrimaryKey(PartyDetailPO record) throws Exception;
}
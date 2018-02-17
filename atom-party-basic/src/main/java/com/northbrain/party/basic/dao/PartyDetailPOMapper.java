package com.northbrain.party.basic.dao;

import java.util.List;

import com.northbrain.party.basic.model.po.PartyDetailPO;
import com.northbrain.party.basic.model.po.RolePO;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

@Mapper
@Component(value="partyDetailPOMapper")
public interface PartyDetailPOMapper
{
    int deleteByPrimaryKey(Integer partyDetailId) throws Exception;

    int insert(PartyDetailPO record) throws Exception;

    int insertSelective(PartyDetailPO record) throws Exception;

    PartyDetailPO selectByPrimaryKey(Integer partyDetailId) throws Exception;

    List<Integer> selectByAttribute(@Param("attribute") String attribute, @Param("value") String value) throws Exception;

    List<PartyDetailPO> selectByPartyId(@Param("partyId") Integer partyId) throws Exception;

    int updateByPrimaryKeySelective(PartyDetailPO record) throws Exception;

    int updateByPrimaryKey(PartyDetailPO record) throws Exception;
}
package com.northbrain.party.role.dao;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Component;

import com.northbrain.party.role.model.po.PartyDetailPO;

@Mapper
@Component(value="partyDetailPOMapper")
public interface PartyDetailPOMapper {
    int deleteByPrimaryKey(Integer partyDetailId);

    int insert(PartyDetailPO record);

    int insertSelective(PartyDetailPO record);

    PartyDetailPO selectByPrimaryKey(Integer partyDetailId);

    int updateByPrimaryKeySelective(PartyDetailPO record);

    int updateByPrimaryKey(PartyDetailPO record);
}
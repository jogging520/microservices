package com.northbrain.party.role.dao;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Component;

import com.northbrain.party.role.model.po.PartyDetailHisPO;

@Mapper
@Component(value="partyDetailHisPOMapper")
public interface PartyDetailHisPOMapper {
    int deleteByPrimaryKey(Integer recordId);

    int insert(PartyDetailHisPO record);

    int insertSelective(PartyDetailHisPO record);

    PartyDetailHisPO selectByPrimaryKey(Integer recordId);

    int updateByPrimaryKeySelective(PartyDetailHisPO record);

    int updateByPrimaryKey(PartyDetailHisPO record);
}
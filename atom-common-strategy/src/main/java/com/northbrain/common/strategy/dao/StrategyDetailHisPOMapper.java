package com.northbrain.common.strategy.dao;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Component;

import com.northbrain.common.strategy.model.po.StrategyDetailHisPO;

/**
 * 类名：策略明细历史映射类
 * 用途：策略明细历史持久化对象的增删改查
 * @author Jiakun
 * @version 1.0
 */
@Mapper
@Component(value="strategyDetailHisPOMapper")
public interface StrategyDetailHisPOMapper
{
    int deleteByPrimaryKey(Integer recordId) throws Exception;

    int insert(StrategyDetailHisPO record) throws Exception;

    int insertSelective(StrategyDetailHisPO record) throws Exception;

    StrategyDetailHisPO selectByPrimaryKey(Integer recordId) throws Exception;

    int updateByPrimaryKeySelective(StrategyDetailHisPO record) throws Exception;

    int updateByPrimaryKey(StrategyDetailHisPO record) throws Exception;
}
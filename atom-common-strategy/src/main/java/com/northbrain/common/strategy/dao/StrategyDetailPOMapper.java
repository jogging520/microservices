package com.northbrain.common.strategy.dao;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Component;

import com.northbrain.common.strategy.model.po.StrategyDetailPO;

/**
 * 类名：策略明细映射类
 * 用途：策略明细持久化对象的增删改查
 * @author Jiakun
 * @version 1.0
 */
@Mapper
@Component(value="strategyDetailPOMapper")
public interface StrategyDetailPOMapper
{
    int deleteByPrimaryKey(Integer strategyDetailId) throws Exception;

    int insert(StrategyDetailPO record) throws Exception;

    int insertSelective(StrategyDetailPO record) throws Exception;

    StrategyDetailPO selectByPrimaryKey(Integer strategyDetailId) throws Exception;

    int updateByPrimaryKeySelective(StrategyDetailPO record) throws Exception;

    int updateByPrimaryKey(StrategyDetailPO record) throws Exception;
}
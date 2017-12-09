package com.northbrain.common.strategy.dao;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Component;

import com.northbrain.common.strategy.model.po.StrategyHisPO;

/**
 * 类名：策略历史映射类
 * 用途：策略历史持久化对象的增删改查
 * @author Jiakun
 * @version 1.0
 */
@Mapper
@Component(value="strategyHisPOMapper")
public interface StrategyHisPOMapper 
{
    int deleteByPrimaryKey(Integer recordId) throws Exception;

    int insert(StrategyHisPO record) throws Exception;

    int insertSelective(StrategyHisPO record) throws Exception;

    StrategyHisPO selectByPrimaryKey(Integer recordId) throws Exception;

    int updateByPrimaryKeySelective(StrategyHisPO record) throws Exception;

    int updateByPrimaryKey(StrategyHisPO record) throws Exception;
}
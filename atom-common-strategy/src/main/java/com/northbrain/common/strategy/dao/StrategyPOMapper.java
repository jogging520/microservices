package com.northbrain.common.strategy.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

import com.northbrain.common.strategy.model.po.StrategyPO;

/**
 * 类名：策略映射类
 * 用途：策略持久化对象的增删改查
 * @author Jiakun
 * @version 1.0
 */
@Mapper
@Component(value="strategyPOMapper")
public interface StrategyPOMapper 
{
    int deleteByPrimaryKey(Integer strategyId) throws Exception;

    int insert(StrategyPO record) throws Exception;

    int insertSelective(StrategyPO record) throws Exception;

    StrategyPO selectByPrimaryKey(Integer strategyId) throws Exception;

    List<StrategyPO> selectByName(@Param("domain") String domain,
                                  @Param("category") String category,
                                  @Param("type") String type,
                                  @Param("name") String name,
                                  @Param("status") Integer status) throws Exception;

    int updateByPrimaryKeySelective(StrategyPO record) throws Exception;

    int updateByPrimaryKey(StrategyPO record) throws Exception;
}
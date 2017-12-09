package com.northbrain.common.strategy.service;

import com.northbrain.base.common.model.vo.ServiceVO;
import com.northbrain.base.common.model.vo.StrategyVO;

/**
 * 类名：策略服务接口
 * 用途：封装策略等Domain，管理事务，并封装ServiceVO
 * @author Jiakun
 *
 */
public interface IStrategyService
{
    /**
     * 方法：创建一条策略
     * @param strategyVO 策略值对象
     * @return 是否操作成功（用ServiceVO封装）
     */
    ServiceVO createStrategy(StrategyVO strategyVO);
}

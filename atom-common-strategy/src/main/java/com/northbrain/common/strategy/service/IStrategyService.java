package com.northbrain.common.strategy.service;

import com.northbrain.base.common.model.vo.basic.ServiceVO;
import com.northbrain.base.common.model.vo.atom.StrategyVO;
import com.northbrain.base.common.model.vo.orch.OrchStrategyVO;

/**
 * 类名：策略服务接口
 * 用途：封装策略等Domain，管理事务，并封装ServiceVO
 * @author Jiakun
 *
 */
public interface IStrategyService
{

    /**
     * 方法：根据名称选取策略
     * @param orchStrategyVO 编排层策略值对象
     * @return 策略清单（用ServiceVO封装）
     */
    ServiceVO readStrategiesByName(OrchStrategyVO orchStrategyVO);
    /**
     * 方法：创建一条策略
     * @param strategyVO 策略值对象
     * @return 是否操作成功（用ServiceVO封装）
     */
    ServiceVO createStrategy(StrategyVO strategyVO);

    /**
     * 方法：更新一条策略
     * @param strategyVO 策略值对象
     * @return 是否操作成功（用ServiceVO封装）
     */
    ServiceVO updateStrategy(StrategyVO strategyVO);

    /**
     * 方法：删除一条策略
     * @param strategyVO 策略值对象
     * @return 是否操作成功（用ServiceVO封装）
     */
    ServiceVO deleteStrategy(StrategyVO strategyVO);
}

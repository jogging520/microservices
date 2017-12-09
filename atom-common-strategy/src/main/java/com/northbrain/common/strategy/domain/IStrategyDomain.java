package com.northbrain.common.strategy.domain;

import com.northbrain.base.common.model.vo.StrategyVO;

/**
 * 类名：策略DOMAIN接口
 * 用途：实现策略的增删改查，对DAO进行封装。
 * @author Jiakun
 * @version 1.0
 *
 */
public interface IStrategyDomain
{
    /**
     * 方法：新建一条策略，根据StrategyVO再转换成相应的PO
     * @param strategyVO 策略值对象
     * @return 是否新增成功
     * @throws Exception 异常
     */
    boolean createStrategy(StrategyVO strategyVO) throws Exception;

    /**
     * 方法：更新一条策略，根据StrategyVO再转换成相应的PO
     * @param strategyVO 策略值对象
     * @return 是否更新成功
     * @throws Exception 异常
     */
    boolean updateStrategy(StrategyVO strategyVO) throws Exception;

    /**
     * 方法：删除一条策略，根据StrategyVO再转换成相应的PO
     * @param strategyVO 策略值对象
     * @return 是否删除成功
     * @throws Exception 异常
     */
    boolean deleteStrategy(StrategyVO strategyVO) throws Exception;
}

package com.northbrain.common.strategy.dto;

import java.util.List;

import com.northbrain.base.common.model.vo.atom.StrategyVO;
import com.northbrain.common.strategy.model.po.StrategyDetailHisPO;
import com.northbrain.common.strategy.model.po.StrategyDetailPO;
import com.northbrain.common.strategy.model.po.StrategyHisPO;
import com.northbrain.common.strategy.model.po.StrategyPO;

/**
 * 类名：策略传输对象接口
 * 用途：用于VO和PO之间的转换
 * @author Jiakun
 * @version 1.0
 *
 */
public interface IStrategyDTO
{
    /**
     * 方法：将策略持久化对象转换成值对象
     * @param strategyPO 策略持久化对象
     * @param strategyDetailPOS 策略明细持久化对象
     * @return 策略值对象
     * @throws Exception 异常
     */
    StrategyVO convertToStrategyVO(StrategyPO strategyPO, List<StrategyDetailPO> strategyDetailPOS) throws Exception;

    /**
     * 方法：将策略值对象转换成策略持久化对象
     * @param strategyVO 策略值对象
     * @return 策略持久化对象
     * @throws Exception 异常
     */
    StrategyPO convertToStrategyPO(StrategyVO strategyVO) throws Exception;

    /**
     * 方法：将策略值对象转换成策略明细持久化对象列表
     * @param strategyVO 策略值对象
     * @return 策略明细持久化对象列表
     * @throws Exception 异常
     */
    List<StrategyDetailPO> convertToStrategyDetailPOS(StrategyVO strategyVO) throws Exception;

    /**
     * 方法：将策略持久化对象转换成策略历史持久化对象
     * @param recordId 操作记录编号
     * @param operateType 操作类型
     * @param strategyPO 策略持久化对象
     * @return 策略历史持久化对象
     * @throws Exception 异常
     */
    StrategyHisPO convertToStrategyHisPO(Integer recordId, String operateType, StrategyPO strategyPO) throws Exception;

    /**
     * 方法：将策略明细持久化对象转换成策略明细历史持久化对象
     * @param recordId 操作记录编号
     * @param operateType 操作类型
     * @param strategyDetailPO 策略明细持久化对象
     * @return 策略明细历史持久化对象
     * @throws Exception 异常
     */
    StrategyDetailHisPO convertToStrategyDetailHisPO(Integer recordId, String operateType, StrategyDetailPO strategyDetailPO) throws Exception;
}

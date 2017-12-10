package com.northbrain.common.strategy.dto.impl;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Component;

import com.northbrain.base.common.exception.ArgumentInputException;
import com.northbrain.base.common.exception.NumberScopeException;
import com.northbrain.base.common.model.bo.Constants;
import com.northbrain.base.common.model.bo.Errors;
import com.northbrain.base.common.model.bo.Hints;
import com.northbrain.base.common.model.vo.StrategyVO;
import com.northbrain.common.strategy.dto.IStrategyDTO;
import com.northbrain.common.strategy.model.po.StrategyDetailHisPO;
import com.northbrain.common.strategy.model.po.StrategyDetailPO;
import com.northbrain.common.strategy.model.po.StrategyHisPO;
import com.northbrain.common.strategy.model.po.StrategyPO;

/**
 * 类名：策略传输对象接口的实现类
 * 用途：用于VO和PO之间的转换
 * @author Jiakun
 * @version 1.0
 *
 */
@Component
public class StrategyDTO implements IStrategyDTO
{
    private static Logger logger = Logger.getLogger(StrategyDTO.class);

    /**
     * 方法：将策略持久化对象转换成值对象
     *
     * @param strategyPO        策略持久化对象
     * @param strategyDetailPOS 策略明细持久化对象
     * @return 策略值对象
     * @throws Exception 异常
     */
    @Override
    public StrategyVO convertToStrategyVO(StrategyPO strategyPO, List<StrategyDetailPO> strategyDetailPOS) throws Exception
    {
        if(strategyPO == null)
        {
            logger.error(Errors.ERROR_BUSINESS_COMMON_ARGUMENT_INPUT_NULL + "strategyPO");
            throw new ArgumentInputException(Errors.ERROR_BUSINESS_COMMON_ARGUMENT_INPUT_EXCEPTION);
        }

        logger.debug(Hints.HINT_BUSINESS_COMMON_PO_VO_CONVERTION);

        StrategyVO strategyVO = new StrategyVO();
        strategyVO.setRecordId(Constants.BUSINESS_COMMON_OPERATION_RECORD_DEFAULT_ID);
        strategyVO.setStrategyId(strategyPO.getStrategyId());
        strategyVO.setName(strategyPO.getName());
        strategyVO.setDomain(strategyPO.getDomain());
        strategyVO.setCategory(strategyPO.getCategory());
        strategyVO.setType(strategyPO.getType());
        strategyVO.setStatus(strategyPO.getStatus());
        strategyVO.setCreateTime(strategyPO.getCreateTime());
        strategyVO.setStatusTime(strategyPO.getStatusTime());
        strategyVO.setDescription(strategyPO.getDescription());

        if(strategyDetailPOS == null || strategyDetailPOS.size() == 0)
        {
            return strategyVO;
        }

        List<StrategyVO.StrategyDetailVO> strategyDetailVOS = new ArrayList<>();

        for(StrategyDetailPO strategyDetailPO: strategyDetailPOS)
        {
            StrategyVO.StrategyDetailVO strategyDetailVO = new StrategyVO.StrategyDetailVO();

            strategyDetailVO.setStrategyDetailId(strategyDetailPO.getStrategyDetailId());
            strategyDetailVO.setAttribute(strategyDetailPO.getAttribute());
            strategyDetailVO.setValue(strategyDetailPO.getValue());
            strategyDetailVO.setStatus(strategyDetailPO.getStatus());
            strategyDetailVO.setCreateTime(strategyDetailPO.getCreateTime());
            strategyDetailVO.setStatusTime(strategyDetailPO.getStatusTime());
            strategyDetailVO.setDescription(strategyDetailPO.getDescription());

            strategyDetailVOS.add(strategyDetailVO);
        }

        strategyVO.setStrategyDetailVOS(strategyDetailVOS);

        return strategyVO;
    }

    /**
     * 方法：将策略值对象转换成策略持久化对象
     *
     * @param strategyVO 策略值对象
     * @return 策略持久化对象
     * @throws Exception 异常
     */
    @Override
    public StrategyPO convertToStrategyPO(StrategyVO strategyVO) throws Exception
    {
        if(strategyVO == null)
        {
            logger.error(Errors.ERROR_BUSINESS_COMMON_ARGUMENT_INPUT_NULL + "strategyVO");
            throw new ArgumentInputException(Errors.ERROR_BUSINESS_COMMON_ARGUMENT_INPUT_EXCEPTION);
        }

        logger.debug(Hints.HINT_BUSINESS_COMMON_PO_VO_CONVERTION);

        StrategyPO strategyPO = new StrategyPO();
        strategyPO.setStrategyId(strategyVO.getStrategyId());
        strategyPO.setName(strategyVO.getName());
        strategyPO.setDomain(strategyVO.getDomain());
        strategyPO.setCategory(strategyVO.getCategory());
        strategyPO.setType(strategyVO.getType());
        strategyPO.setStatus(strategyVO.getStatus());
        strategyPO.setCreateTime(strategyVO.getCreateTime());
        strategyPO.setStatusTime(strategyVO.getStatusTime());
        strategyPO.setDescription(strategyVO.getDescription());

        return strategyPO;
    }

    /**
     * 方法：将策略值对象转换成策略明细持久化对象列表
     *
     * @param strategyVO 策略值对象
     * @return 策略明细持久化对象列表
     * @throws Exception 异常
     */
    @Override
    public List<StrategyDetailPO> convertToStrategyDetailPOS(StrategyVO strategyVO) throws Exception
    {
        if(strategyVO == null)
        {
            logger.error(Errors.ERROR_BUSINESS_COMMON_ARGUMENT_INPUT_NULL + "strategyVO");
            throw new ArgumentInputException(Errors.ERROR_BUSINESS_COMMON_ARGUMENT_INPUT_EXCEPTION);
        }

        if(strategyVO.getStrategyDetailVOS() == null)
        {
            logger.error(Errors.ERROR_BUSINESS_COMMON_ARGUMENT_INPUT_NULL + "strategyVO.getStrategyDetailVOS()");
            throw new ArgumentInputException(Errors.ERROR_BUSINESS_COMMON_ARGUMENT_INPUT_EXCEPTION);
        }

        logger.debug(Hints.HINT_BUSINESS_COMMON_PO_VO_CONVERTION);

        List<StrategyDetailPO> strategyDetailPOS = new ArrayList<>();

        for(StrategyVO.StrategyDetailVO strategyDetailVO: strategyVO.getStrategyDetailVOS())
        {
            StrategyDetailPO strategyDetailPO = new StrategyDetailPO();

            strategyDetailPO.setStrategyDetailId(strategyDetailVO.getStrategyDetailId());
            strategyDetailPO.setStrategyId(strategyVO.getStrategyId());
            strategyDetailPO.setAttribute(strategyDetailVO.getAttribute());
            strategyDetailPO.setValue(strategyDetailVO.getValue());
            strategyDetailPO.setStatus(strategyDetailVO.getStatus());
            strategyDetailPO.setCreateTime(strategyDetailVO.getCreateTime());
            strategyDetailPO.setStatusTime(strategyDetailVO.getStatusTime());
            strategyDetailPO.setDescription(strategyDetailVO.getDescription());

            strategyDetailPOS.add(strategyDetailPO);
        }

        return strategyDetailPOS;
    }

    /**
     * 方法：将策略持久化对象转换成策略历史持久化对象
     *
     * @param recordId    操作记录编号
     * @param operateType 操作类型
     * @param strategyPO  策略持久化对象
     * @return 策略历史持久化对象
     * @throws Exception 异常
     */
    @Override
    public StrategyHisPO convertToStrategyHisPO(Integer recordId, String operateType,
                                                StrategyPO strategyPO) throws Exception
    {
        if(recordId <= 0)
        {
            logger.error(Errors.ERROR_BUSINESS_COMMON_NUMBER_SCOPE + "recordId:" + recordId);
            throw new NumberScopeException(Errors.ERROR_BUSINESS_COMMON_NUMBER_SCOPE_EXCEPTION);
        }

        if(operateType == null || operateType.equals(""))
        {
            logger.error(Errors.ERROR_BUSINESS_COMMON_ARGUMENT_INPUT_NULL + "operateType");
            throw new ArgumentInputException(Errors.ERROR_BUSINESS_COMMON_ARGUMENT_INPUT_EXCEPTION);
        }

        if(strategyPO == null)
        {
            logger.error(Errors.ERROR_BUSINESS_COMMON_ARGUMENT_INPUT_NULL + "strategyPO");
            throw new ArgumentInputException(Errors.ERROR_BUSINESS_COMMON_ARGUMENT_INPUT_EXCEPTION);
        }

        logger.debug(Hints.HINT_BUSINESS_COMMON_PO_VO_CONVERTION);

        StrategyHisPO strategyHisPO = new StrategyHisPO();
        strategyHisPO.setRecordId(recordId);
        strategyHisPO.setOperateType(operateType);
        strategyHisPO.setStrategyId(strategyPO.getStrategyId());
        strategyHisPO.setName(strategyPO.getName());
        strategyHisPO.setDomain(strategyPO.getDomain());
        strategyHisPO.setCategory(strategyPO.getCategory());
        strategyHisPO.setType(strategyPO.getType());
        strategyHisPO.setStatus(strategyPO.getStatus());
        strategyHisPO.setCreateTime(strategyPO.getCreateTime());
        strategyHisPO.setStatusTime(strategyPO.getStatusTime());
        strategyHisPO.setDescription(strategyPO.getDescription());

        return strategyHisPO;
    }

    /**
     * 方法：将策略明细持久化对象转换成策略明细历史持久化对象
     *
     * @param recordId         操作记录编号
     * @param operateType      操作类型
     * @param strategyDetailPO 策略明细持久化对象
     * @return 策略明细历史持久化对象
     * @throws Exception 异常
     */
    @Override
    public StrategyDetailHisPO convertToStrategyDetailHisPO(Integer recordId, String operateType,
                                                            StrategyDetailPO strategyDetailPO) throws Exception
    {
        if(recordId <= 0)
        {
            logger.error(Errors.ERROR_BUSINESS_COMMON_NUMBER_SCOPE + "recordId:" + recordId);
            throw new NumberScopeException(Errors.ERROR_BUSINESS_COMMON_NUMBER_SCOPE_EXCEPTION);
        }

        if(operateType == null || operateType.equals(""))
        {
            logger.error(Errors.ERROR_BUSINESS_COMMON_ARGUMENT_INPUT_NULL + "operateType");
            throw new ArgumentInputException(Errors.ERROR_BUSINESS_COMMON_ARGUMENT_INPUT_EXCEPTION);
        }

        if(strategyDetailPO == null)
        {
            logger.error(Errors.ERROR_BUSINESS_COMMON_ARGUMENT_INPUT_NULL + "strategyDetailPO");
            throw new ArgumentInputException(Errors.ERROR_BUSINESS_COMMON_ARGUMENT_INPUT_EXCEPTION);
        }

        logger.debug(Hints.HINT_BUSINESS_COMMON_PO_VO_CONVERTION);

        StrategyDetailHisPO strategyDetailHisPO = new StrategyDetailHisPO();

        strategyDetailHisPO.setRecordId(recordId);
        strategyDetailHisPO.setOperateType(operateType);
        strategyDetailHisPO.setStrategyDetailId(strategyDetailPO.getStrategyDetailId());
        strategyDetailHisPO.setStrategyId(strategyDetailPO.getStrategyId());
        strategyDetailHisPO.setAttribute(strategyDetailPO.getAttribute());
        strategyDetailHisPO.setValue(strategyDetailPO.getValue());
        strategyDetailHisPO.setStatus(strategyDetailPO.getStatus());
        strategyDetailHisPO.setCreateTime(strategyDetailPO.getCreateTime());
        strategyDetailHisPO.setStatusTime(strategyDetailPO.getStatusTime());
        strategyDetailHisPO.setDescription(strategyDetailPO.getDescription());

        return strategyDetailHisPO;
    }
}

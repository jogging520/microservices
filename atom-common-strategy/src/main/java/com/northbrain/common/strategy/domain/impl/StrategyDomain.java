package com.northbrain.common.strategy.domain.impl;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.northbrain.base.common.exception.ArgumentInputException;
import com.northbrain.base.common.exception.ObjectNullException;
import com.northbrain.base.common.model.bo.BaseType;
import com.northbrain.base.common.model.bo.Errors;
import com.northbrain.base.common.model.vo.StrategyVO;
import com.northbrain.common.strategy.dao.StrategyDetailHisPOMapper;
import com.northbrain.common.strategy.dao.StrategyDetailPOMapper;
import com.northbrain.common.strategy.dao.StrategyHisPOMapper;
import com.northbrain.common.strategy.dao.StrategyPOMapper;
import com.northbrain.common.strategy.domain.IStrategyDomain;
import com.northbrain.common.strategy.dto.IStrategyDTO;
import com.northbrain.common.strategy.exception.StrategyException;
import com.northbrain.common.strategy.model.po.StrategyDetailHisPO;
import com.northbrain.common.strategy.model.po.StrategyDetailPO;
import com.northbrain.common.strategy.model.po.StrategyHisPO;
import com.northbrain.common.strategy.model.po.StrategyPO;

/**
 * 类名：策略DOMAIN接口的实现类
 * 用途：实现策略的增删改查，对DAO进行封装。
 * @author Jiakun
 * @version 1.0
 *
 */
@Component
public class StrategyDomain implements IStrategyDomain
{
    private static Logger logger = Logger.getLogger(StrategyDomain.class);
    private final StrategyPOMapper strategyPOMapper;
    private final StrategyHisPOMapper strategyHisPOMapper;
    private final StrategyDetailPOMapper strategyDetailPOMapper;
    private final StrategyDetailHisPOMapper strategyDetailHisPOMapper;
    private final IStrategyDTO strategyDTO;

    @Autowired
    public StrategyDomain(StrategyPOMapper strategyPOMapper, StrategyHisPOMapper strategyHisPOMapper,
                          StrategyDetailPOMapper strategyDetailPOMapper,
                          StrategyDetailHisPOMapper strategyDetailHisPOMapper,
                          IStrategyDTO strategyDTO)
    {
        this.strategyPOMapper = strategyPOMapper;
        this.strategyHisPOMapper = strategyHisPOMapper;
        this.strategyDetailPOMapper = strategyDetailPOMapper;
        this.strategyDetailHisPOMapper = strategyDetailHisPOMapper;
        this.strategyDTO = strategyDTO;
    }

    /**
     * 方法：新建一条策略，根据StrategyVO再转换成相应的PO
     *
     * @param strategyVO 策略值对象
     * @return 是否新增成功
     * @throws Exception 异常
     */
    @Override
    public boolean createStrategy(StrategyVO strategyVO) throws Exception
    {
        if(strategyVO == null)
        {
            logger.error(Errors.ERROR_BUSINESS_COMMON_ARGUMENT_INPUT_NULL + "strategyVO");
            throw new ArgumentInputException(Errors.ERROR_BUSINESS_COMMON_ARGUMENT_INPUT_EXCEPTION);
        }

        if(strategyPOMapper == null)
        {
            logger.error(Errors.ERROR_BUSINESS_COMMON_OBJECT_NULL + "strategyPOMapper");
            throw new ObjectNullException(Errors.ERROR_BUSINESS_COMMON_OBJECT_NULL_EXCEPTION);
        }

        if(strategyHisPOMapper == null)
        {
            logger.error(Errors.ERROR_BUSINESS_COMMON_OBJECT_NULL + "strategyHisPOMapper");
            throw new ObjectNullException(Errors.ERROR_BUSINESS_COMMON_OBJECT_NULL_EXCEPTION);
        }

        if(strategyDetailPOMapper == null)
        {
            logger.error(Errors.ERROR_BUSINESS_COMMON_OBJECT_NULL + "strategyDetailPOMapper");
            throw new ObjectNullException(Errors.ERROR_BUSINESS_COMMON_OBJECT_NULL_EXCEPTION);
        }

        if(strategyDetailHisPOMapper == null)
        {
            logger.error(Errors.ERROR_BUSINESS_COMMON_OBJECT_NULL + "strategyDetailHisPOMapper");
            throw new ObjectNullException(Errors.ERROR_BUSINESS_COMMON_OBJECT_NULL_EXCEPTION);
        }

        if(strategyDTO == null)
        {
            logger.error(Errors.ERROR_BUSINESS_COMMON_OBJECT_NULL + "strategyDTO");
            throw new ObjectNullException(Errors.ERROR_BUSINESS_COMMON_OBJECT_NULL_EXCEPTION);
        }

        logger.debug(strategyVO);

        StrategyPO strategyPO = strategyDTO.convertToStrategyPO(strategyVO);

        if(strategyPO == null)
        {
            logger.error(Errors.ERROR_BUSINESS_COMMON_OBJECT_NULL + "strategyPO");
            throw new ObjectNullException(Errors.ERROR_BUSINESS_COMMON_OBJECT_NULL_EXCEPTION);
        }

        //插入在用表的同时，插入历史表，以便后续业务统计、分析、恢复等操作。
        if(strategyPOMapper.selectByPrimaryKey(strategyPO.getStrategyId()) == null)
        {
            if(strategyPOMapper.insert(strategyPO) == 0)
            {
                logger.error(Errors.ERROR_BUSINESS_COMMON_SECURITY_STRATEGY_INSERT + String.valueOf(strategyPO.getStrategyId()));
                throw new StrategyException(Errors.ERROR_BUSINESS_COMMON_SECURITY_STRATEGY_EXCEPTION);
            }

            StrategyHisPO strategyHisPO = strategyDTO.convertToStrategyHisPO(strategyVO.getRecordId(), BaseType.OPERATETYPE.CREATE.name(), strategyPO);

            if(strategyHisPO == null)
            {
                logger.error(Errors.ERROR_BUSINESS_COMMON_OBJECT_NULL + "strategyHisPO");
                throw new ObjectNullException(Errors.ERROR_BUSINESS_COMMON_OBJECT_NULL_EXCEPTION);
            }

            if(strategyHisPOMapper.insert(strategyHisPO) == 0)
            {
                logger.error(Errors.ERROR_BUSINESS_COMMON_SECURITY_STRATEGY_INSERT + String.valueOf(strategyHisPO.getStrategyId()));
                throw new StrategyException(Errors.ERROR_BUSINESS_COMMON_SECURITY_STRATEGY_EXCEPTION);
            }
        }

        if(strategyVO.getStrategyDetailVOS() != null && strategyVO.getStrategyDetailVOS().size() > 0)
        {
            List<StrategyDetailPO> strategyDetailPOS = strategyDTO.convertToStrategyDetailPOS(strategyVO);

            if (strategyDetailPOS == null || strategyDetailPOS.size() == 0)
            {
                logger.error(Errors.ERROR_BUSINESS_COMMON_OBJECT_NULL + "strategyDetailPOS");
                throw new ObjectNullException(Errors.ERROR_BUSINESS_COMMON_OBJECT_NULL_EXCEPTION);
            }

            for(StrategyDetailPO strategyDetailPO: strategyDetailPOS)
            {
                //插入在用表的同时，插入历史表，以便后续业务统计、分析、恢复等操作。
                if(strategyDetailPOMapper.selectByPrimaryKey(strategyDetailPO.getStrategyDetailId()) == null)
                {
                    if (strategyDetailPOMapper.insert(strategyDetailPO) == 0)
                    {
                        logger.error(Errors.ERROR_BUSINESS_COMMON_SECURITY_STRATEGY_INSERT + String.valueOf(strategyDetailPO.getStrategyDetailId()));
                        throw new StrategyException(Errors.ERROR_BUSINESS_COMMON_SECURITY_STRATEGY_EXCEPTION);
                    }

                    StrategyDetailHisPO strategyDetailHisPO = strategyDTO.convertToStrategyDetailHisPO(strategyVO.getRecordId(), BaseType.OPERATETYPE.CREATE.name(), strategyDetailPO);

                    if(strategyDetailHisPO == null)
                    {
                        logger.error(Errors.ERROR_BUSINESS_COMMON_OBJECT_NULL + "strategyDetailHisPO");
                        throw new ObjectNullException(Errors.ERROR_BUSINESS_COMMON_OBJECT_NULL_EXCEPTION);
                    }

                    if (strategyDetailHisPOMapper.insert(strategyDetailHisPO) == 0)
                    {
                        logger.error(Errors.ERROR_BUSINESS_COMMON_SECURITY_STRATEGY_INSERT + String.valueOf(strategyDetailHisPO.getStrategyDetailId()));
                        throw new StrategyException(Errors.ERROR_BUSINESS_COMMON_SECURITY_STRATEGY_EXCEPTION);
                    }
                }
            }
        }

        return true;
    }

    /**
     * 方法：更新一条策略，根据StrategyVO再转换成相应的PO
     *
     * @param strategyVO 策略值对象
     * @return 是否更新成功
     * @throws Exception 异常
     */
    @Override
    public boolean updateStrategy(StrategyVO strategyVO) throws Exception
    {
        if(strategyVO == null)
        {
            logger.error(Errors.ERROR_BUSINESS_COMMON_ARGUMENT_INPUT_NULL + "strategyVO");
            throw new ArgumentInputException(Errors.ERROR_BUSINESS_COMMON_ARGUMENT_INPUT_EXCEPTION);
        }

        if(strategyPOMapper == null)
        {
            logger.error(Errors.ERROR_BUSINESS_COMMON_OBJECT_NULL + "strategyPOMapper");
            throw new ObjectNullException(Errors.ERROR_BUSINESS_COMMON_OBJECT_NULL_EXCEPTION);
        }

        if(strategyHisPOMapper == null)
        {
            logger.error(Errors.ERROR_BUSINESS_COMMON_OBJECT_NULL + "strategyHisPOMapper");
            throw new ObjectNullException(Errors.ERROR_BUSINESS_COMMON_OBJECT_NULL_EXCEPTION);
        }

        if(strategyDetailPOMapper == null)
        {
            logger.error(Errors.ERROR_BUSINESS_COMMON_OBJECT_NULL + "strategyDetailPOMapper");
            throw new ObjectNullException(Errors.ERROR_BUSINESS_COMMON_OBJECT_NULL_EXCEPTION);
        }

        if(strategyDetailHisPOMapper == null)
        {
            logger.error(Errors.ERROR_BUSINESS_COMMON_OBJECT_NULL + "strategyDetailHisPOMapper");
            throw new ObjectNullException(Errors.ERROR_BUSINESS_COMMON_OBJECT_NULL_EXCEPTION);
        }

        if(strategyDTO == null)
        {
            logger.error(Errors.ERROR_BUSINESS_COMMON_OBJECT_NULL + "strategyDTO");
            throw new ObjectNullException(Errors.ERROR_BUSINESS_COMMON_OBJECT_NULL_EXCEPTION);
        }

        logger.debug(strategyVO);

        StrategyPO strategyPO = strategyDTO.convertToStrategyPO(strategyVO);

        if(strategyPO == null)
        {
            logger.error(Errors.ERROR_BUSINESS_COMMON_OBJECT_NULL + "strategyPO");
            throw new ObjectNullException(Errors.ERROR_BUSINESS_COMMON_OBJECT_NULL_EXCEPTION);
        }

        //更新在用表的同时，插入历史表，以便后续业务统计、分析、恢复等操作。
        if(strategyPOMapper.selectByPrimaryKey(strategyPO.getStrategyId()) == null)
        {
            if(strategyPOMapper.updateByPrimaryKey(strategyPO) == 0)
            {
                logger.error(Errors.ERROR_BUSINESS_COMMON_SECURITY_STRATEGY_UPDATE + String.valueOf(strategyPO.getStrategyId()));
                throw new StrategyException(Errors.ERROR_BUSINESS_COMMON_SECURITY_STRATEGY_EXCEPTION);
            }

            StrategyHisPO strategyHisPO = strategyDTO.convertToStrategyHisPO(strategyVO.getRecordId(), BaseType.OPERATETYPE.UPDATE.name(), strategyPO);

            if(strategyHisPO == null)
            {
                logger.error(Errors.ERROR_BUSINESS_COMMON_OBJECT_NULL + "strategyHisPO");
                throw new ObjectNullException(Errors.ERROR_BUSINESS_COMMON_OBJECT_NULL_EXCEPTION);
            }

            //对于更新的也是插入历史表
            if(strategyHisPOMapper.insert(strategyHisPO) == 0)
            {
                logger.error(Errors.ERROR_BUSINESS_COMMON_SECURITY_STRATEGY_INSERT + String.valueOf(strategyHisPO.getStrategyId()));
                throw new StrategyException(Errors.ERROR_BUSINESS_COMMON_SECURITY_STRATEGY_EXCEPTION);
            }
        }

        if(strategyVO.getStrategyDetailVOS() != null && strategyVO.getStrategyDetailVOS().size() > 0)
        {
            List<StrategyDetailPO> strategyDetailPOS = strategyDTO.convertToStrategyDetailPOS(strategyVO);

            if (strategyDetailPOS == null || strategyDetailPOS.size() == 0)
            {
                logger.error(Errors.ERROR_BUSINESS_COMMON_OBJECT_NULL + "strategyDetailPOS");
                throw new ObjectNullException(Errors.ERROR_BUSINESS_COMMON_OBJECT_NULL_EXCEPTION);
            }

            for(StrategyDetailPO strategyDetailPO: strategyDetailPOS)
            {
                //插入在用表的同时，插入历史表，以便后续业务统计、分析、恢复等操作。
                if(strategyDetailPOMapper.selectByPrimaryKey(strategyDetailPO.getStrategyDetailId()) == null)
                {
                    if (strategyDetailPOMapper.updateByPrimaryKey(strategyDetailPO) == 0)
                    {
                        logger.error(Errors.ERROR_BUSINESS_COMMON_SECURITY_STRATEGY_UPDATE + String.valueOf(strategyDetailPO.getStrategyDetailId()));
                        throw new StrategyException(Errors.ERROR_BUSINESS_COMMON_SECURITY_STRATEGY_EXCEPTION);
                    }

                    StrategyDetailHisPO strategyDetailHisPO = strategyDTO.convertToStrategyDetailHisPO(strategyVO.getRecordId(), BaseType.OPERATETYPE.CREATE.name(), strategyDetailPO);

                    if(strategyDetailHisPO == null)
                    {
                        logger.error(Errors.ERROR_BUSINESS_COMMON_OBJECT_NULL + "strategyDetailHisPO");
                        throw new ObjectNullException(Errors.ERROR_BUSINESS_COMMON_OBJECT_NULL_EXCEPTION);
                    }

                    //对于更新的也是插入历史表
                    if (strategyDetailHisPOMapper.insert(strategyDetailHisPO) == 0)
                    {
                        logger.error(Errors.ERROR_BUSINESS_COMMON_SECURITY_STRATEGY_INSERT + String.valueOf(strategyDetailHisPO.getStrategyDetailId()));
                        throw new StrategyException(Errors.ERROR_BUSINESS_COMMON_SECURITY_STRATEGY_EXCEPTION);
                    }
                }
            }
        }

        return true;
    }

    /**
     * 方法：删除一条策略，根据StrategyVO再转换成相应的PO
     *
     * @param strategyVO 策略值对象
     * @return 是否删除成功
     * @throws Exception 异常
     */
    @Override
    public boolean deleteStrategy(StrategyVO strategyVO) throws Exception
    {
        if(strategyVO == null)
        {
            logger.error(Errors.ERROR_BUSINESS_COMMON_ARGUMENT_INPUT_NULL + "strategyVO");
            throw new ArgumentInputException(Errors.ERROR_BUSINESS_COMMON_ARGUMENT_INPUT_EXCEPTION);
        }

        if(strategyPOMapper == null)
        {
            logger.error(Errors.ERROR_BUSINESS_COMMON_OBJECT_NULL + "strategyPOMapper");
            throw new ObjectNullException(Errors.ERROR_BUSINESS_COMMON_OBJECT_NULL_EXCEPTION);
        }

        if(strategyHisPOMapper == null)
        {
            logger.error(Errors.ERROR_BUSINESS_COMMON_OBJECT_NULL + "strategyHisPOMapper");
            throw new ObjectNullException(Errors.ERROR_BUSINESS_COMMON_OBJECT_NULL_EXCEPTION);
        }

        if(strategyDetailPOMapper == null)
        {
            logger.error(Errors.ERROR_BUSINESS_COMMON_OBJECT_NULL + "strategyDetailPOMapper");
            throw new ObjectNullException(Errors.ERROR_BUSINESS_COMMON_OBJECT_NULL_EXCEPTION);
        }

        if(strategyDetailHisPOMapper == null)
        {
            logger.error(Errors.ERROR_BUSINESS_COMMON_OBJECT_NULL + "strategyDetailHisPOMapper");
            throw new ObjectNullException(Errors.ERROR_BUSINESS_COMMON_OBJECT_NULL_EXCEPTION);
        }

        if(strategyDTO == null)
        {
            logger.error(Errors.ERROR_BUSINESS_COMMON_OBJECT_NULL + "strategyDTO");
            throw new ObjectNullException(Errors.ERROR_BUSINESS_COMMON_OBJECT_NULL_EXCEPTION);
        }

        logger.debug(strategyVO);

        StrategyPO strategyPO = strategyDTO.convertToStrategyPO(strategyVO);

        if(strategyPO == null)
        {
            logger.error(Errors.ERROR_BUSINESS_COMMON_OBJECT_NULL + "strategyPO");
            throw new ObjectNullException(Errors.ERROR_BUSINESS_COMMON_OBJECT_NULL_EXCEPTION);
        }

        //插入在用表的同时，插入历史表，以便后续业务统计、分析、恢复等操作。
        if(strategyPOMapper.selectByPrimaryKey(strategyPO.getStrategyId()) == null)
        {
            if(strategyPOMapper.deleteByPrimaryKey(strategyPO.getStrategyId()) == 0)
            {
                logger.error(Errors.ERROR_BUSINESS_COMMON_SECURITY_STRATEGY_DELETE + String.valueOf(strategyPO.getStrategyId()));
                throw new StrategyException(Errors.ERROR_BUSINESS_COMMON_SECURITY_STRATEGY_EXCEPTION);
            }

            StrategyHisPO strategyHisPO = strategyDTO.convertToStrategyHisPO(strategyVO.getRecordId(), BaseType.OPERATETYPE.DELETE.name(), strategyPO);

            if(strategyHisPO == null)
            {
                logger.error(Errors.ERROR_BUSINESS_COMMON_OBJECT_NULL + "strategyHisPO");
                throw new ObjectNullException(Errors.ERROR_BUSINESS_COMMON_OBJECT_NULL_EXCEPTION);
            }

            //对于删除的也是插入历史表
            if(strategyHisPOMapper.insert(strategyHisPO) == 0)
            {
                logger.error(Errors.ERROR_BUSINESS_COMMON_SECURITY_STRATEGY_INSERT + String.valueOf(strategyHisPO.getStrategyId()));
                throw new StrategyException(Errors.ERROR_BUSINESS_COMMON_SECURITY_STRATEGY_EXCEPTION);
            }
        }

        if(strategyVO.getStrategyDetailVOS() != null && strategyVO.getStrategyDetailVOS().size() > 0)
        {
            List<StrategyDetailPO> strategyDetailPOS = strategyDTO.convertToStrategyDetailPOS(strategyVO);

            if (strategyDetailPOS == null || strategyDetailPOS.size() == 0)
            {
                logger.error(Errors.ERROR_BUSINESS_COMMON_OBJECT_NULL + "strategyDetailPOS");
                throw new ObjectNullException(Errors.ERROR_BUSINESS_COMMON_OBJECT_NULL_EXCEPTION);
            }

            for(StrategyDetailPO strategyDetailPO: strategyDetailPOS)
            {
                //插入在用表的同时，插入历史表，以便后续业务统计、分析、恢复等操作。
                if(strategyDetailPOMapper.selectByPrimaryKey(strategyDetailPO.getStrategyDetailId()) == null)
                {
                    if (strategyDetailPOMapper.deleteByPrimaryKey(strategyDetailPO.getStrategyDetailId()) == 0)
                    {
                        logger.error(Errors.ERROR_BUSINESS_COMMON_SECURITY_STRATEGY_DELETE + String.valueOf(strategyDetailPO.getStrategyDetailId()));
                        throw new StrategyException(Errors.ERROR_BUSINESS_COMMON_SECURITY_STRATEGY_EXCEPTION);
                    }

                    StrategyDetailHisPO strategyDetailHisPO = strategyDTO.convertToStrategyDetailHisPO(strategyVO.getRecordId(), BaseType.OPERATETYPE.CREATE.name(), strategyDetailPO);

                    if(strategyDetailHisPO == null)
                    {
                        logger.error(Errors.ERROR_BUSINESS_COMMON_OBJECT_NULL + "strategyDetailHisPO");
                        throw new ObjectNullException(Errors.ERROR_BUSINESS_COMMON_OBJECT_NULL_EXCEPTION);
                    }

                    //对于删除的也是插入历史表
                    if (strategyDetailHisPOMapper.insert(strategyDetailHisPO) == 0)
                    {
                        logger.error(Errors.ERROR_BUSINESS_COMMON_SECURITY_STRATEGY_INSERT + String.valueOf(strategyDetailHisPO.getStrategyDetailId()));
                        throw new StrategyException(Errors.ERROR_BUSINESS_COMMON_SECURITY_STRATEGY_EXCEPTION);
                    }
                }
            }
        }

        return true;
    }
}

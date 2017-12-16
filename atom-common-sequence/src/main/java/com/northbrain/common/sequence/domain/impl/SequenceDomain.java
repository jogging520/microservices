package com.northbrain.common.sequence.domain.impl;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.northbrain.base.common.exception.ObjectNullException;
import com.northbrain.base.common.model.bo.Constants;
import com.northbrain.base.common.model.bo.Errors;
import com.northbrain.base.common.model.bo.Names;
import com.northbrain.base.launcher.dao.ISequenceDAO;
import com.northbrain.common.sequence.domain.ISequenceDomain;

/**
 * 类名：序列号域接口的实现类
 * 用途：通过各种方式获取唯一的序列号，包括ZOOKEEPER、REDIS、数据库等。
 * @author Jiakun
 * @version 1.0
 *
 */
@Component
public class SequenceDomain implements ISequenceDomain
{
    private static Logger logger = Logger.getLogger(SequenceDomain.class);
    private final ISequenceDAO sequenceDAO;

    @Autowired
    public SequenceDomain(ISequenceDAO sequenceDAO)
    {
        this.sequenceDAO = sequenceDAO;
    }

    /**
     * 方法：获取全局唯一操作记录序列号
     * 利用zk来生成，但有最大值的限制，不能超过INTEGER的最大值，大概在20亿左右
     * @return 全局唯一操作记录序列号
     */
    @Override
    public int readOperationRecordId() throws Exception
    {
        if(sequenceDAO == null)
        {
            logger.error(Errors.ERROR_BUSINESS_COMMON_OBJECT_NULL + "sequenceDAO");
            throw new ObjectNullException(Errors.ERROR_BUSINESS_COMMON_OBJECT_NULL_EXCEPTION);
        }

        return Constants.BUSINESS_COMMON_BASIC_SEQUENCE +
                sequenceDAO.getNextValue(Constants.BUSINESS_COMMON_NODE_SEPARATOR + Constants.STORAGE_ZOOKEEPER_SEQUENCE_NAMESPACE,
                        Names.STORAGE_ZOOKEEPER_GLOBAL_OPERATION_RECORD_SEQUENCE.getName());
    }

    /**
     * 方法：获取全局唯一注册序列号
     * 利用zk来生成，但有最大值的限制，不能超过INTEGER的最大值，大概在20亿左右
     * @return 全局唯一注册序列号
     */
    @Override
    public int readRegistryId() throws Exception
    {
        if(sequenceDAO == null)
        {
            logger.error(Errors.ERROR_BUSINESS_COMMON_OBJECT_NULL + "sequenceDAO");
            throw new ObjectNullException(Errors.ERROR_BUSINESS_COMMON_OBJECT_NULL_EXCEPTION);
        }

        return Constants.BUSINESS_COMMON_BASIC_SEQUENCE +
                sequenceDAO.getNextValue(Constants.BUSINESS_COMMON_NODE_SEPARATOR + Constants.STORAGE_ZOOKEEPER_SEQUENCE_NAMESPACE,
                        Names.STORAGE_ZOOKEEPER_GLOBAL_REGISTRY_SEQUENCE.getName());
    }

    /**
     * 方法：获取全局唯一参与者序列号
     * 利用zk来生成，但有最大值的限制，不能超过INTEGER的最大值，大概在20亿左右
     * @return 全局唯一参与者序列号
     */
    @Override
    public int readPartyId() throws Exception
    {
        if(sequenceDAO == null)
        {
            logger.error(Errors.ERROR_BUSINESS_COMMON_OBJECT_NULL + "sequenceDAO");
            throw new ObjectNullException(Errors.ERROR_BUSINESS_COMMON_OBJECT_NULL_EXCEPTION);
        }

        return Constants.BUSINESS_COMMON_BASIC_SEQUENCE +
                sequenceDAO.getNextValue(Constants.BUSINESS_COMMON_NODE_SEPARATOR + Constants.STORAGE_ZOOKEEPER_SEQUENCE_NAMESPACE,
                        Names.STORAGE_ZOOKEEPER_GLOBAL_PARTY_SEQUENCE.getName());
    }
}

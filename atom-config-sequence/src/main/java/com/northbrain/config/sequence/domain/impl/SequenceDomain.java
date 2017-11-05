package com.northbrain.config.sequence.domain.impl;

import com.northbrain.base.launcher.dao.ISequenceDAO;
import com.northbrain.config.sequence.domain.ISequenceDomain;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * 类名：序列号域接口的实现类
 * 用途：通过各种方式获取唯一的序列号，包括ZOOKEEPER、REDIS、数据库等。
 * @author Jiakun
 * @version 1.0
 *
 */
public class SequenceDomain implements ISequenceDomain
{
    private static Logger logger = Logger.getLogger(SequenceDomain.class);
    @Autowired
    private ISequenceDAO sequenceDAO;

    /**
     * 方法：获取全局唯一序列号
     * @return 全局唯一序列号
     */
    public int getGlobalSequence() throws Exception
    {
        return 0;
    }

    /**
     * 方法：获取产品域唯一序列号
     * @return 产品唯一序列号
     */
    public int getProductSequence() throws Exception
    {
        return 0;
    }

    /**
     * 方法：获取资源域唯一序列号
     * @return 资源域唯一序列号
     */
    public int getResourceSequence() throws Exception
    {
        return 0;
    }

    /**
     * 方法：获取服务域唯一序列号
     * @return 服务域唯一序列号
     */
    public int getServiceSequence() throws Exception
    {
        return 0;
    }

    /**
     * 方法：获取全局唯一序列号
     * @return 全局唯一序列号
     */
    public int getPartySequence() throws Exception
    {
        return 0;
    }

    /**
     * 方法：获取计费域唯一序列号
     * @return 计费域唯一序列号
     */
    public int getChargeSequence() throws Exception
    {
        return 0;
    }

    /**
     * 方法：获取全局唯一序列号
     * @return 全局唯一序列号
     */
    public int getLogSequence() throws Exception
    {
        return 0;
    }

    /**
     * 方法：获取全局唯一序列号
     * @return 全局唯一序列号
     */
    public int getConfigSequence() throws Exception
    {
        return 0;
    }

    /**
     * 方法：获取安全域唯一序列号
     * @return 安全域唯一序列号
     */
    public int getSecuritySequence() throws Exception
    {
        return 0;
    }

    /**
     * 方法：获取搜索域唯一序列号
     * @return 搜索域唯一序列号
     */
    public int getSearchSequence() throws Exception
    {
        return 0;
    }

    /**
     * 方法：获取报表域唯一序列号
     * @return 报表域唯一序列号
     */
    public int getReportSequence() throws Exception
    {
        return 0;
    }

    /**
     * 方法：获取统计域唯一序列号
     * @return 统计域唯一序列号
     */
    public int getStatisticsSequence() throws Exception
    {
        return 0;
    }
}

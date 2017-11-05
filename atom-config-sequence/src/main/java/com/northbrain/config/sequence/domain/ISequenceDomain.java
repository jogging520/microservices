package com.northbrain.config.sequence.domain;

/**
 * 类名：序列号域接口
 * 用途：通过各种方式获取唯一的序列号，包括ZOOKEEPER、REDIS、数据库等。
 * @author Jiakun
 * @version 1.0
 *
 */
public interface ISequenceDomain
{
    /**
     * 方法：获取全局唯一序列号
     * @return 全局唯一序列号
     */
    int getGlobalSequence() throws Exception;

    /**
     * 方法：获取产品域唯一序列号
     * @return 产品唯一序列号
     */
    int getProductSequence() throws Exception;

    /**
     * 方法：获取资源域唯一序列号
     * @return 资源域唯一序列号
     */
    int getResourceSequence() throws Exception;

    /**
     * 方法：获取服务域唯一序列号
     * @return 服务域唯一序列号
     */
    int getServiceSequence() throws Exception;

    /**
     * 方法：获取全局唯一序列号
     * @return 全局唯一序列号
     */
    int getPartySequence() throws Exception;

    /**
     * 方法：获取计费域唯一序列号
     * @return 计费域唯一序列号
     */
    int getChargeSequence() throws Exception;

    /**
     * 方法：获取全局唯一序列号
     * @return 全局唯一序列号
     */
    int getLogSequence() throws Exception;

    /**
     * 方法：获取全局唯一序列号
     * @return 全局唯一序列号
     */
    int getConfigSequence() throws Exception;

    /**
     * 方法：获取安全域唯一序列号
     * @return 安全域唯一序列号
     */
    int getSecuritySequence() throws Exception;

    /**
     * 方法：获取搜索域唯一序列号
     * @return 搜索域唯一序列号
     */
    int getSearchSequence() throws Exception;

    /**
     * 方法：获取报表域唯一序列号
     * @return 报表域唯一序列号
     */
    int getReportSequence() throws Exception;

    /**
     * 方法：获取统计域唯一序列号
     * @return 统计域唯一序列号
     */
    int getStatisticsSequence() throws Exception;
}

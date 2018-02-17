package com.northbrain.common.sequence.domain;

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
     * 方法：获取全局唯一操作记录序列号
     * @return 全局唯一操作记录序列号
     */
    int readOperationRecordId() throws Exception;

    /**
     * 方法：获取全局唯一注册序列号
     * @return 全局唯一注册序列号
     */
    int readRegistryId() throws Exception;

    /**
     * 方法：获取全局唯一登录序列号
     * @return 全局唯一登录序列号
     */
    int readLoginId() throws Exception;

    /**
     * 方法：获取全局唯一参与者序列号
     * @return 全局唯一参与者序列号
     */
    int readPartyId() throws Exception;
}

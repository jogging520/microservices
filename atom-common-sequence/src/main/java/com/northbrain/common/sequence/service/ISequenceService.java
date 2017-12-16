package com.northbrain.common.sequence.service;

import com.northbrain.base.common.model.vo.basic.ServiceVO;

/**
 * 类名：获取序列号的接口。
 * 用途：获取下一个全局序列号
 * @author Jiakun
 * @version 1.0
 */
public interface ISequenceService
{
    /**
     * 方法：获取下一个全局操作记录序列号
     * @return 下一个全局操作记录序列号的ServiceVO封装对象
     */
    ServiceVO readOperationRecordId();

    /**
     * 方法：获取下一个全局注册序列号
     * @return 下一个全局注册序列号的ServiceVO封装对象
     */
    ServiceVO readRegistryId();

    /**
     * 方法：获取下一个全局参与者序列号
     * @return 下一个全局参与者序列号的ServiceVO封装对象
     */
    ServiceVO readPartyId();
}

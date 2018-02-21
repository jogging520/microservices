package com.northbrain.common.flowcontrol.service;

import com.northbrain.base.common.model.vo.atom.FlowControlVO;
import com.northbrain.base.common.model.vo.basic.ServiceVO;

/**
 * 类名：流量管控接口
 * 用途：封装是否进行流控，并封装ServiceVO
 * @author Jiakun
 * @version 1.0
 */
public interface IFlowControlService
{
    /**
     * 方法：获取特定的流量管控权限
     * @param flowControlVO 流控值对象
     * @return ServiceVO封装类
     */
    ServiceVO readFlowControl(FlowControlVO flowControlVO);
}

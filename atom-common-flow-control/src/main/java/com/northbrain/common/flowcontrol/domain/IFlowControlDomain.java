package com.northbrain.common.flowcontrol.domain;

import com.northbrain.base.common.model.vo.atom.FlowControlVO;

/**
 * 类名：流量控制域接口
 * 用途：实现流量控制等功能。
 * @author Jiakun
 * @version 1.0
 *
 */
public interface IFlowControlDomain
{
    /**
     * 方法：获取特定的流量管控权限
     *
     * @param flowControlVO 流控值对象
     * @return 是否放行
     * @throws Exception 异常
     */
    boolean readFlowControl(FlowControlVO flowControlVO) throws Exception;
}

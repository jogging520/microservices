package com.northbrain.common.flowcontrol.domain.impl;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Component;

import com.northbrain.base.common.model.vo.atom.FlowControlVO;
import com.northbrain.common.flowcontrol.domain.IFlowControlDomain;

/**
 * 类名：流量控制域接口的实现类
 * 用途：实现流量控制等功能。
 * @author Jiakun
 * @version 1.0
 *
 */
@Component
public class FlowControlDomain implements IFlowControlDomain
{
    private static Logger logger = Logger.getLogger(FlowControlDomain.class);

    /**
     * 方法：获取特定的流量管控权限
     *
     * @param flowControlVO 流控值对象
     * @return 是否放行
     * @throws Exception 异常
     */
    @Override
    public boolean readFlowControl(FlowControlVO flowControlVO) throws Exception
    {
        return false;
    }
}

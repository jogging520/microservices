package com.northbrain.general.gateway.service;

import com.northbrain.base.common.model.vo.orch.OrchAccessControlVO;

/**
 * 类名：网关服务接口
 * 用途：操作访问控制等相关操作。
 * @author Jiakun
 * @version 1.0
 */
public interface IGatewayService
{
    /**
     * 方法：访问控制
     * @param orchAccessControlVO 编排层访问控制值对象
     * @return 是否允许访问
     */
    Boolean readAccessControl(OrchAccessControlVO orchAccessControlVO);
}
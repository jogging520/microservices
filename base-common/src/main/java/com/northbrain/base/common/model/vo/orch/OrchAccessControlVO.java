package com.northbrain.base.common.model.vo.orch;

/**
 * 类名：编排层访问控制值对象类
 * 用途：在zuul调用除registry和login的其他编排层服务，通过该类进行token和服务面的访问控制校验。
 * @author Jiakun
 * @version 1.0
 */
public class OrchAccessControlVO
{
    //JWT
    private String jsonWebToken;

    //要调用的服务名称
    private String serviceName;

    //描述
    private String description;

    public String getJsonWebToken() {
        return jsonWebToken;
    }

    public void setJsonWebToken(String jsonWebToken) {
        this.jsonWebToken = jsonWebToken;
    }

    public String getServiceName() {
        return serviceName;
    }

    public void setServiceName(String serviceName) {
        this.serviceName = serviceName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}

package com.northbrain.list.course.domain;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.*;

import com.northbrain.base.common.model.bo.Constants;
import com.northbrain.base.common.model.vo.ServiceVO;
import com.northbrain.list.course.domain.hystrix.HystrixStorageDomain;

/**
 * 类名存储DOMAIN接口
 * 用途：用于通知Feign组件对该接口进行代理
 */
@FeignClient(name = Constants.BUSINESS_RESOURCE_STORAGE_ATOM_MICROSERVICE, fallback = HystrixStorageDomain.class)
public interface IStorageDomain
{
    /**
     * 方法：获取指定ID存储的详细信息
     * @return 存储的详细信息
     */
    @RequestMapping(value=Constants.URI_ATOM_PRODUCT_GET_COURSES_IN_USED, method = RequestMethod.GET, produces = Constants.BUSINESS_COMMON_HTTP_REQUEST_PRODUCERS)
    @ResponseBody
    ServiceVO readStorage(@PathVariable("storageId")int storageId);
}

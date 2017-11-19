package com.northbrain.list.course.dao;

import java.util.List;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.*;

import com.northbrain.base.common.model.bo.Constants;
import com.northbrain.list.course.dao.hystrix.HystrixStorageDAO;

/**
 * 类名存储DAO接口
 * 用途：用于通知Feign组件对该接口进行代理
 */
@FeignClient(name = Constants.BUSINESS_RESOURCE_STORAGE_ATOM_MICROSERVICE, fallback = HystrixStorageDAO.class)
@RequestMapping(Constants.URI_ATOM_RESOURCE_STORAGE_REQUEST_MAPPING)
public interface IStorageDAO
{
    /**
     * 方法：通过原子服务获取指定ID存储的详细信息
     * @return 存储的详细信息
     */
    @RequestMapping(value=Constants.URI_ATOM_RESOURCE_STORAGE_SPECIFIED_REQUEST_MAPPING, method = RequestMethod.GET, produces = Constants.BUSINESS_COMMON_HTTP_REQUEST_PRODUCERS)
    @ResponseBody
    String readAtomStorage(@PathVariable("storageId")int storageId) throws Exception;

    /**
     * 方法：读取一组存储信息
     * @return 以ServiceVO封装的存储信息
     */
    @RequestMapping(method = RequestMethod.POST, produces = Constants.BUSINESS_COMMON_HTTP_REQUEST_PRODUCERS, consumes = Constants.BUSINESS_COMMON_HTTP_REQUEST_CONSUMERS)
    @ResponseBody
    String readAtomStorages(@RequestBody List<Integer> storageIds) throws Exception;
}

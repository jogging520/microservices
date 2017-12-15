package com.northbrain.list.course.dao;

import java.util.List;

import com.alibaba.fastjson.JSON;
import com.northbrain.base.common.model.bo.Errors;
import com.northbrain.base.common.model.bo.Hints;
import com.northbrain.base.common.model.vo.basic.ServiceVO;
import org.apache.log4j.Logger;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.*;

import com.northbrain.base.common.model.bo.Constants;

/**
 * 类名存储DAO接口
 * 用途：用于通知Feign组件对该接口进行代理
 */
@Component(value="storageDAO")
@FeignClient(name = Constants.BUSINESS_RESOURCE_STORAGE_ATOM_MICROSERVICE, fallback = IStorageDAO.HystrixStorageDAO.class)
public interface IStorageDAO
{
    /**
     * 方法：通过原子服务获取指定ID存储的详细信息
     * @return 存储的详细信息
     */
    @RequestMapping(value = Constants.URI_ATOM_RESOURCE_STORAGE_SPECIFIED_REQUEST_MAPPING, method = RequestMethod.GET, produces = Constants.BUSINESS_COMMON_HTTP_REQUEST_PRODUCERS)
    @ResponseBody
    String readAtomStorage(@PathVariable("storageId")int storageId) throws Exception;

    /**
     * 方法：读取一组存储信息
     * @return 以ServiceVO封装的存储信息
     */
    @RequestMapping(value = Constants.URI_ATOM_RESOURCE_STORAGE_REQUEST_MAPPING, method = RequestMethod.POST, produces = Constants.BUSINESS_COMMON_HTTP_REQUEST_PRODUCERS, consumes = Constants.BUSINESS_COMMON_HTTP_REQUEST_CONSUMERS)
    @ResponseBody
    String readAtomStorages(@RequestBody List<Integer> storageIds) throws Exception;

    /**
     * 类名：存储DOMAIN接口的熔断器实现类
     * 用途：用于Hystrix熔断时fallback调用
     */
    @Component(value="hystrixStorageDAO")
    class HystrixStorageDAO implements IStorageDAO
    {
        private static Logger logger = Logger.getLogger(HystrixStorageDAO.class);

        /**
         * 方法：Hystrix熔断时的fallback调用
         *
         * @param storageId 存储信息编号
         * @return 存储的详细信息
         */
        @Override
        public String readAtomStorage(int storageId) throws Exception
        {
            logger.info(Hints.HINT_SYSTEM_PROCESS_CALL_HYSTRIX_DAO + "readAtomStorage");

            ServiceVO serviceVO = new ServiceVO();
            serviceVO.setResponseCodeAndDesc(Errors.ERROR_SYSTEM_SERVICE_HYSTRIX_EXCEPTION);

            return JSON.toJSONString(serviceVO);
        }

        /**
         * 方法：读取一组存储信息
         *
         * @param storageIds 存储信息编号列表
         * @return 以ServiceVO封装的存储信息
         */
        @Override
        public String readAtomStorages(List<Integer> storageIds) throws Exception
        {
            logger.info(Hints.HINT_SYSTEM_PROCESS_CALL_HYSTRIX_DAO + "readAtomStorage");

            ServiceVO serviceVO = new ServiceVO();
            serviceVO.setResponseCodeAndDesc(Errors.ERROR_SYSTEM_SERVICE_HYSTRIX_EXCEPTION);

            return JSON.toJSONString(serviceVO);
        }
    }
}

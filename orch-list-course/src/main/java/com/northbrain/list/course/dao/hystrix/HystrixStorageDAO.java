package com.northbrain.list.course.dao.hystrix;

import java.util.List;

import org.apache.log4j.Logger;

import com.alibaba.fastjson.JSON;
import com.northbrain.base.common.model.bo.Errors;
import com.northbrain.base.common.model.bo.Hints;
import com.northbrain.base.common.model.vo.ServiceVO;
import com.northbrain.list.course.dao.IStorageDAO;

/**
 * 类名：存储DOMAIN接口的熔断器实现类
 * 用途：用于Hystrix熔断时fallback调用
 */
public class HystrixStorageDAO implements IStorageDAO
{
    private static Logger logger = Logger.getLogger(HystrixStorageDAO.class);

    /**
     * 方法：Hystrix熔断时的fallback调用
     *
     * @param storageId
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
     * @param storageIds
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

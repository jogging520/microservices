package com.northbrain.list.course.domain.hystrix;

import org.apache.log4j.Logger;

import com.northbrain.base.common.model.bo.Errors;
import com.northbrain.base.common.model.bo.Hints;
import com.northbrain.base.common.model.vo.ServiceVO;
import com.northbrain.list.course.domain.IStorageDomain;

/**
 * 类名：存储DOMAIN接口的熔断器实现类
 * 用途：用于Hystrix熔断时fallback调用
 */
public class HystrixStorageDomain implements IStorageDomain
{
    private static Logger logger = Logger.getLogger(HystrixStorageDomain.class);

    /**
     * 方法：获取指定ID存储的详细信息
     *
     * @param storageId
     * @return 存储的详细信息
     */
    @Override
    public ServiceVO readStorage(int storageId)
    {
        logger.info(Hints.HINT_SYSTEM_PROCESS_CALL_HYSTRIX_DOMAIN + "readInUsedCourses");

        ServiceVO serviceVO = new ServiceVO();
        serviceVO.setResponseCodeAndDesc(Errors.ERROR_SYSTEM_SERVICE_HYSTRIX_EXCEPTION);

        return serviceVO;
    }
}

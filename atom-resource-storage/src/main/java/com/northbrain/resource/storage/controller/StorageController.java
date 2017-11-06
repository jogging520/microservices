package com.northbrain.resource.storage.controller;

import javax.websocket.server.PathParam;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.northbrain.base.common.model.bo.Constants;
import com.northbrain.base.common.model.bo.Errors;
import com.northbrain.base.common.model.bo.Hints;
import com.northbrain.base.common.model.vo.ServiceVO;
import com.northbrain.resource.storage.service.IStorageService;

/**
 * 类名：存储控制层类（原子服务）
 * 用途：解析http servlet，调用service层服务，返回给服务编排层应答数据。
 */
@RestController
@RequestMapping(Constants.URI_ATOM_RESOURCE_DOMAIN_REQUEST_MAPPING)
public class StorageController
{
    private static Logger logger = Logger.getLogger(StorageController.class);
    private final IStorageService storageService;

    @Autowired
    public StorageController(IStorageService storageService)
    {
        this.storageService = storageService;
    }

    /**
     * 方法：读取存储信息
     * @return 以ServiceVO封装的课程列表
     */
    @RequestMapping(value= Constants.URI_ATOM_RESOURCE_GET_STORAGE_SPECIFIED, method = RequestMethod.GET, produces = Constants.BUSINESS_COMMON_HTTP_REQUEST_PRODUCERS)
    @ResponseBody
    public ServiceVO readStorage(@PathParam("storageId")int storageId)
    {
        logger.info(Hints.HINT_SYSTEM_PROCESS_CALL_CONTROLLER + "readStorage");

        if(storageService == null)
        {
            logger.error(Errors.ERROR_BUSINESS_COMMON_OBJECT_NULL + "storageService");

            return new ServiceVO();
        }

        return storageService.readStorage(storageId);
    }
}

package com.northbrain.base.common.model.vo;

import java.util.Date;
import java.util.List;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.annotation.JSONField;
import com.northbrain.base.common.model.bo.Constants;

/**
 * 类名：存储值对象类
 * 用途：用于持久层以上的存储对象传递
 * @author Jiakun
 * @version 1.0
 */
public class StorageVO extends BasicVO
{
    //存储编号
    private Integer storageId;

    //资源位置
    private String uri;

    private List<StorageDetailVO> storageDetailVOS;

    public Integer getStorageId() {
        return storageId;
    }

    public void setStorageId(Integer storageId) {
        this.storageId = storageId;
    }

    public String getUri() {
        return uri;
    }

    public void setUri(String uri) {
        this.uri = uri == null ? null : uri.trim();
    }

    public List<StorageDetailVO> getStorageDetailVOS() {
        return storageDetailVOS;
    }

    public void setStorageDetailVOS(List<StorageDetailVO> storageDetailVOS) {
        this.storageDetailVOS = storageDetailVOS;
    }

    //存储明细值对象类
    public static class StorageDetailVO extends BasicDetailVO
    {
        //存储明细编号
        private Integer storageDetailId;

        public Integer getStorageDetailId() {
            return storageDetailId;
        }

        public void setStorageDetailId(Integer storageDetailId) {
            this.storageDetailId = storageDetailId;
        }
    }
}

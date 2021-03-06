package com.northbrain.base.common.model.vo.atom;

import java.util.List;

import com.northbrain.base.common.model.vo.basic.BasicDetailVO;
import com.northbrain.base.common.model.vo.basic.BasicVO;

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

    //资源编号
    private Integer resourceId;

    //资源位置
    private String uri;

    private List<StorageDetailVO> storageDetailVOS;

    public Integer getStorageId() {
        return storageId;
    }

    public void setStorageId(Integer storageId) {
        this.storageId = storageId;
    }

    public Integer getResourceId() {
        return resourceId;
    }

    public void setResourceId(Integer resourceId) {
        this.resourceId = resourceId;
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

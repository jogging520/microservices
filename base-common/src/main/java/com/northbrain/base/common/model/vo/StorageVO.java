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
public class StorageVO
{
    //操作编号
    private Integer recordId;

    //存储编号
    private Integer storageId;

    //类别
    private String category;

    //类型
    private String type;

    //资源位置
    private String uri;

    //状态
    private Integer status;

    //创建时间
    @JSONField(format=Constants.BUSINESS_COMMON_JSON_RESPONSE_DATE_FORMART)
    private Date createTime;

    //状态时间
    @JSONField(format=Constants.BUSINESS_COMMON_JSON_RESPONSE_DATE_FORMART)
    private Date statusTime;

    //描述
    private String description;

    private List<StorageDetailVO> storageDetailVOS;

    public Integer getRecordId() {
        return recordId;
    }

    public void setRecordId(Integer recordId) {
        this.recordId = recordId;
    }

    public Integer getStorageId() {
        return storageId;
    }

    public void setStorageId(Integer storageId) {
        this.storageId = storageId;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category == null ? null : category.trim();
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type == null ? null : type.trim();
    }

    public String getUri() {
        return uri;
    }

    public void setUri(String uri) {
        this.uri = uri == null ? null : uri.trim();
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getStatusTime() {
        return statusTime;
    }

    public void setStatusTime(Date statusTime) {
        this.statusTime = statusTime;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description == null ? null : description.trim();
    }

    public List<StorageDetailVO> getStorageDetailVOS() {
        return storageDetailVOS;
    }

    public void setStorageDetailVOS(List<StorageDetailVO> storageDetailVOS) {
        this.storageDetailVOS = storageDetailVOS;
    }

    public String toString()
    {
        return JSON.toJSONString(this);
    }

    //存储明细值对象类
    public static class StorageDetailVO
    {
        //存储明细编号
        private Integer storageDetailId;

        //属性
        private String attribute;

        //取值
        private String value;

        //状态
        private Integer status;

        //创建时间
        private Date createTime;

        //状态时间
        private Date statusTime;

        //描述
        private String description;

        public Integer getStorageDetailId() {
            return storageDetailId;
        }

        public void setStorageDetailId(Integer storageDetailId) {
            this.storageDetailId = storageDetailId;
        }

        public String getAttribute() {
            return attribute;
        }

        public void setAttribute(String attribute) {
            this.attribute = attribute == null ? null : attribute.trim();
        }

        public String getValue() {
            return value;
        }

        public void setValue(String value) {
            this.value = value == null ? null : value.trim();
        }

        public Integer getStatus() {
            return status;
        }

        public void setStatus(Integer status) {
            this.status = status;
        }

        public Date getCreateTime() {
            return createTime;
        }

        public void setCreateTime(Date createTime) {
            this.createTime = createTime;
        }

        public Date getStatusTime() {
            return statusTime;
        }

        public void setStatusTime(Date statusTime) {
            this.statusTime = statusTime;
        }

        public String getDescription() {
            return description;
        }

        public void setDescription(String description) {
            this.description = description == null ? null : description.trim();
        }
    }
}

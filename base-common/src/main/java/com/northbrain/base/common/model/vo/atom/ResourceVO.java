package com.northbrain.base.common.model.vo.atom;

import java.util.List;

import com.northbrain.base.common.model.vo.basic.BasicDetailVO;
import com.northbrain.base.common.model.vo.basic.BasicVO;

/**
 * 类名：资源值对象类
 * 用途：用于持久层以上的角色对象传递
 * @author Jiakun
 * @version 1.0
 */
public class ResourceVO extends BasicVO
{
    //资源编号
    private Integer resourceId;

    //组织机构编号
    private Integer organizationId;

    //实体编号
    private Integer entityId;

    private List<ResourceDetailVO> resourceDetailVOS;

    public Integer getResourceId() {
        return resourceId;
    }

    public void setResourceId(Integer resourceId) {
        this.resourceId = resourceId;
    }

    public Integer getOrganizationId() {
        return organizationId;
    }

    public void setOrganizationId(Integer organizationId) {
        this.organizationId = organizationId;
    }

    public Integer getEntityId() {
        return entityId;
    }

    public void setEntityId(Integer entityId) {
        this.entityId = entityId;
    }

    public List<ResourceDetailVO> getResourceDetailVOS() {
        return resourceDetailVOS;
    }

    public void setResourceDetailVOS(List<ResourceDetailVO> resourceDetailVOS) {
        this.resourceDetailVOS = resourceDetailVOS;
    }

    //策略明细值对象类
    public static class ResourceDetailVO extends BasicDetailVO
    {
        //策略明细编号
        private Integer resourceDetailId;

        public Integer getResourceDetailId() {
            return resourceDetailId;
        }

        public void setResourceDetailId(Integer resourceDetailId) {
            this.resourceDetailId = resourceDetailId;
        }
    }
}

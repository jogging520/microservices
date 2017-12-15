package com.northbrain.base.common.model.vo.atom;

import com.northbrain.base.common.model.vo.basic.BasicVO;

/**
 * 类名：隶属关系值对象类，该类记录了参与者隶属于组织的关系记录。
 * 引用方-->隶属关系-->组织及具体的组织机构
 * 用途：用于隶属关系数据库对对象在domain层及以上的数据传递。
 * @author Jiakun
 * @version 1.0
 */
public class SubjectionVO extends BasicVO
{
    //隶属编号
    private Integer subjectionId;

    //参与者编号
    private Integer partyId;

    //组织机构编号
    private Integer organizationId;

    //具体的组织机构编号
    private Integer entityId;

    public Integer getSubjectionId() {
        return subjectionId;
    }

    public void setSubjectionId(Integer subjectionId) {
        this.subjectionId = subjectionId;
    }

    public Integer getPartyId() {
        return partyId;
    }

    public void setPartyId(Integer partyId) {
        this.partyId = partyId;
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
}

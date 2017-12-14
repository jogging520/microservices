package com.northbrain.base.common.model.vo;

/**
 * 类名：组织机构值对象类，该类定义了组织机构的层级关系、名称、类型等。
 * 用途：用于组织机构在domain层及以上数据传递。
 * @author Jiakun
 * @version 1.0
 */
public class OrganizationVO extends BasicVO
{
    //组织机构编号
    private Integer organizationId;

    //名称
    private String name;

    //组织机构的编码（按一定的规则）
    private String code;

    //父组织机构的编号（用于递归）
    private Integer parentOrganizationId;

    public Integer getOrganizationId() {
        return organizationId;
    }

    public void setOrganizationId(Integer organizationId) {
        this.organizationId = organizationId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code == null ? null : code.trim();
    }

    public Integer getParentOrganizationId() {
        return parentOrganizationId;
    }

    public void setParentOrganizationId(Integer parentOrganizationId) {
        this.parentOrganizationId = parentOrganizationId;
    }
}

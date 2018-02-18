package com.northbrain.base.common.model.vo.orch;

/**
 * 类名：编排层策略值对象类
 * 用途：用于编排层调用策略时候传输值对象
 * @author Jiakun
 * @version 1.0
 */
public class OrchStrategyVO
{
    private String domain;

    private String category;

    private String type;

    private String name;

    private String description;

    public String getDomain() {
        return domain;
    }

    public void setDomain(String domain) {
        this.domain = domain;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}

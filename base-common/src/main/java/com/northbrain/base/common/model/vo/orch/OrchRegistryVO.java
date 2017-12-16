package com.northbrain.base.common.model.vo.orch;

/**
 * 类名：编排层注册信息值对象类，包括姓名、职业、渠道、年龄、性别、密码、注册渠道、类型、邮箱、联系方式等。
 * 后端把这些信息分解到registry和party、partyDetail中。
 * 用途：用于表现层与编排层信息交互，封装注册相关的信息。
 * @author Jiakun
 * @version 1.0
 */
public class OrchRegistryVO
{
    private String name;

    private String sex;

    private int age;

    private String alias;

    private String password;

    private String domain;

    private String category;

    private String type;

    private String email;

    private String phone;

    private String description;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getAlias() {
        return alias;
    }

    public void setAlias(String alias) {
        this.alias = alias;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}

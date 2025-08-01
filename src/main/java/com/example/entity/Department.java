package com.example.entity;


import java.util.List;

/**
 * 部门信息
 */
public class Department {
    /** ID */
    private Integer id;
    /** 名称 */
    private String name;
    /** 地址 */
    private String address;
    /** 父级ID */
    private Integer pid;
    /** 层级 */
    private Integer level;
    private List Children;



    public List getChildren() {
        return Children;
    }

    public void setChildren(List children) {
        Children = children;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Integer getPid() {
        return pid;
    }

    public void setPid(Integer pid) {
        this.pid = pid;
    }

    public Integer getLevel() {
        return level;
    }

    public void setLevel(Integer level) {
        this.level = level;
    }

}
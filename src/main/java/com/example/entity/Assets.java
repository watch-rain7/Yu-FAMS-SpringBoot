package com.example.entity;

import cn.hutool.core.annotation.Alias;

import java.math.BigDecimal;

/**
 * 资产信息
 */
public class Assets {
    /** ID */
    @Alias("ID")
    private Integer id;
    /** 资产名称 */
    @Alias("资产名称")
    private String name;
    /** 资产分类 */
    @Alias("资产分类")
    private String category;
    /** 资产编号 */
    @Alias("资产编号")
    private String no;
    /** 资产图片 */
    @Alias("资产图片")
    private String img;
    /** 资产型号 */
    @Alias("资产型号")
    private String model;
    /** 数量 */
    @Alias("数量")
    private Integer num;
    /** 购置日期 */
    @Alias("购置日期")
    private String date;
    /** 初始价值 */
    @Alias("初始价值")
    private BigDecimal money;
    @Alias("当前价值")
    private BigDecimal currentValue; // 当前价值
    /** 折旧方法 */
    @Alias("折旧方法")
    private String depreciate;
    @Alias("折旧率")
    private BigDecimal depreciationRate; // 折旧率
    /** 使用部门ID */
    @Alias("使用部门ID")
    private Integer departmentId;
    @Alias("使用部门名称")
    private String departmentName;
    /** 责任人 */
    @Alias("责任人ID")
    private Integer staffId;
    @Alias("责任人名称")
    private String staffName;
    /** 存放地点 */
    @Alias("存放地点")
    private String location;
    /** 状态 */
    @Alias("状态")
    private String status; // 资产状态（正常、维修中、报废）
    /** 备注 */
    @Alias("备注")
    private String comment;

    public BigDecimal getCurrentValue() {
        return currentValue;
    }

    public void setCurrentValue(BigDecimal currentValue) {
        this.currentValue = currentValue;
    }

    public BigDecimal getDepreciationRate() {
        return depreciationRate;
    }

    public void setDepreciationRate(BigDecimal depreciationRate) {
        this.depreciationRate = depreciationRate;
    }

    public String getStaffName() {
        return staffName;
    }

    public void setStaffName(String staffName) {
        this.staffName = staffName;
    }

    public String getDepartmentName() {
        return departmentName;
    }

    public void setDepartmentName(String departmentName) {
        this.departmentName = departmentName;
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

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getNo() {
        return no;
    }

    public void setNo(String no) {
        this.no = no;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public Integer getNum() {
        return num;
    }

    public void setNum(Integer num) {
        this.num = num;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public BigDecimal getMoney() {
        return money;
    }

    public void setMoney(BigDecimal money) {
        this.money = money;
    }

    public String getDepreciate() {
        return depreciate;
    }

    public void setDepreciate(String depreciate) {
        this.depreciate = depreciate;
    }

    public Integer getDepartmentId() {
        return departmentId;
    }

    public void setDepartmentId(Integer departmentId) {
        this.departmentId = departmentId;
    }

    public Integer getStaffId() {
        return staffId;
    }

    public void setStaffId(Integer staffId) {
        this.staffId = staffId;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

}
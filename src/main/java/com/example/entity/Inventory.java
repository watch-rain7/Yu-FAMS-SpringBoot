package com.example.entity;

import cn.hutool.core.annotation.Alias;

import java.math.BigDecimal;

/**
 * 资产盘点信息
 */
public class Inventory {
    /** ID */
    @Alias("ID")
    private Integer id;
    /** 资产ID */
    @Alias("资产ID")
    private Integer assetsId;
    /** 资产名称 */
    @Alias("资产名称")
    private String assetsName;
    /** 资产编号 */
    @Alias("资产编号")
    private String assetsNo;
    /** 盘点时间 */
    @Alias("盘点时间")
    private String inventoryTime;
    /** 盘点人ID */
    @Alias("盘点人ID")
    private Integer staffId;
    /** 盘点人姓名 */
    @Alias("盘点人姓名")
    private String staffName;
    /** 盘点结果 */
    @Alias("盘点结果")
    private String result; // 正常、异常、丢失
    /** 盘点状态 */
    @Alias("盘点状态")
    private String status; // 待盘点、已盘点、已确认
    /** 盘点位置 */
    @Alias("盘点位置")
    private String location;
    /** 备注 */
    @Alias("备注")
    private String comment;
    /** 创建时间 */
    @Alias("创建时间")
    private String createTime;
    /** 更新时间 */
    @Alias("更新时间")
    private String updateTime;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getAssetsId() {
        return assetsId;
    }

    public void setAssetsId(Integer assetsId) {
        this.assetsId = assetsId;
    }

    public String getAssetsName() {
        return assetsName;
    }

    public void setAssetsName(String assetsName) {
        this.assetsName = assetsName;
    }

    public String getAssetsNo() {
        return assetsNo;
    }

    public void setAssetsNo(String assetsNo) {
        this.assetsNo = assetsNo;
    }

    public String getInventoryTime() {
        return inventoryTime;
    }

    public void setInventoryTime(String inventoryTime) {
        this.inventoryTime = inventoryTime;
    }

    public Integer getStaffId() {
        return staffId;
    }

    public void setStaffId(Integer staffId) {
        this.staffId = staffId;
    }

    public String getStaffName() {
        return staffName;
    }

    public void setStaffName(String staffName) {
        this.staffName = staffName;
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public String getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(String updateTime) {
        this.updateTime = updateTime;
    }
}

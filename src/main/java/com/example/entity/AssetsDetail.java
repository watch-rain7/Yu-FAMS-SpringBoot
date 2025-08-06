package com.example.entity;

import cn.hutool.core.annotation.Alias;

/**
 * 资产详情记录
 */
public class AssetsDetail {
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
    /** 操作类型 */
    @Alias("操作类型")
    private String operationType; // 入库、领用、调拨、维修、盘点、报废等
    /** 操作时间 */
    @Alias("操作时间")
    private String operationTime;
    /** 操作人ID */
    @Alias("操作人ID")
    private Integer operatorId;
    /** 操作人姓名 */
    @Alias("操作人姓名")
    private String operatorName;
    /** 操作前状态 */
    @Alias("操作前状态")
    private String beforeStatus;
    /** 操作后状态 */
    @Alias("操作后状态")
    private String afterStatus;
    /** 操作前使用人ID */
    @Alias("操作前使用人ID")
    private Integer beforeStaffId;
    /** 操作前使用人姓名 */
    @Alias("操作前使用人姓名")
    private String beforeStaffName;
    /** 操作后使用人ID */
    @Alias("操作后使用人ID")
    private Integer afterStaffId;
    /** 操作后使用人姓名 */
    @Alias("操作后使用人姓名")
    private String afterStaffName;
    /** 操作前部门ID */
    @Alias("操作前部门ID")
    private Integer beforeDepartmentId;
    /** 操作前部门名称 */
    @Alias("操作前部门名称")
    private String beforeDepartmentName;
    /** 操作后部门ID */
    @Alias("操作后部门ID")
    private Integer afterDepartmentId;
    /** 操作后部门名称 */
    @Alias("操作后部门名称")
    private String afterDepartmentName;
    /** 操作前位置 */
    @Alias("操作前位置")
    private String beforeLocation;
    /** 操作后位置 */
    @Alias("操作后位置")
    private String afterLocation;
    /** 操作描述 */
    @Alias("操作描述")
    private String description;
    /** 备注 */
    @Alias("备注")
    private String comment;
    /** 创建时间 */
    @Alias("创建时间")
    private String createTime;

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

    public String getOperationType() {
        return operationType;
    }

    public void setOperationType(String operationType) {
        this.operationType = operationType;
    }

    public String getOperationTime() {
        return operationTime;
    }

    public void setOperationTime(String operationTime) {
        this.operationTime = operationTime;
    }

    public Integer getOperatorId() {
        return operatorId;
    }

    public void setOperatorId(Integer operatorId) {
        this.operatorId = operatorId;
    }

    public String getOperatorName() {
        return operatorName;
    }

    public void setOperatorName(String operatorName) {
        this.operatorName = operatorName;
    }

    public String getBeforeStatus() {
        return beforeStatus;
    }

    public void setBeforeStatus(String beforeStatus) {
        this.beforeStatus = beforeStatus;
    }

    public String getAfterStatus() {
        return afterStatus;
    }

    public void setAfterStatus(String afterStatus) {
        this.afterStatus = afterStatus;
    }

    public Integer getBeforeStaffId() {
        return beforeStaffId;
    }

    public void setBeforeStaffId(Integer beforeStaffId) {
        this.beforeStaffId = beforeStaffId;
    }

    public String getBeforeStaffName() {
        return beforeStaffName;
    }

    public void setBeforeStaffName(String beforeStaffName) {
        this.beforeStaffName = beforeStaffName;
    }

    public Integer getAfterStaffId() {
        return afterStaffId;
    }

    public void setAfterStaffId(Integer afterStaffId) {
        this.afterStaffId = afterStaffId;
    }

    public String getAfterStaffName() {
        return afterStaffName;
    }

    public void setAfterStaffName(String afterStaffName) {
        this.afterStaffName = afterStaffName;
    }

    public Integer getBeforeDepartmentId() {
        return beforeDepartmentId;
    }

    public void setBeforeDepartmentId(Integer beforeDepartmentId) {
        this.beforeDepartmentId = beforeDepartmentId;
    }

    public String getBeforeDepartmentName() {
        return beforeDepartmentName;
    }

    public void setBeforeDepartmentName(String beforeDepartmentName) {
        this.beforeDepartmentName = beforeDepartmentName;
    }

    public Integer getAfterDepartmentId() {
        return afterDepartmentId;
    }

    public void setAfterDepartmentId(Integer afterDepartmentId) {
        this.afterDepartmentId = afterDepartmentId;
    }

    public String getAfterDepartmentName() {
        return afterDepartmentName;
    }

    public void setAfterDepartmentName(String afterDepartmentName) {
        this.afterDepartmentName = afterDepartmentName;
    }

    public String getBeforeLocation() {
        return beforeLocation;
    }

    public void setBeforeLocation(String beforeLocation) {
        this.beforeLocation = beforeLocation;
    }

    public String getAfterLocation() {
        return afterLocation;
    }

    public void setAfterLocation(String afterLocation) {
        this.afterLocation = afterLocation;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
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
} 
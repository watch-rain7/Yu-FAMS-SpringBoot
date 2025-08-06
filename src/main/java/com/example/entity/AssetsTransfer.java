package com.example.entity;

import cn.hutool.core.annotation.Alias;

/**
 * 资产调拨信息
 */
public class AssetsTransfer {
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
    /** 调拨时间 */
    @Alias("调拨时间")
    private String transferTime;
    /** 调拨人ID */
    @Alias("调拨人ID")
    private Integer transferStaffId;
    /** 调拨人姓名 */
    @Alias("调拨人姓名")
    private String transferStaffName;
    /** 原使用人ID */
    @Alias("原使用人ID")
    private Integer fromStaffId;
    /** 原使用人姓名 */
    @Alias("原使用人姓名")
    private String fromStaffName;
    /** 原部门ID */
    @Alias("原部门ID")
    private Integer fromDepartmentId;
    /** 原部门名称 */
    @Alias("原部门名称")
    private String fromDepartmentName;
    /** 新使用人ID */
    @Alias("新使用人ID")
    private Integer toStaffId;
    /** 新使用人姓名 */
    @Alias("新使用人姓名")
    private String toStaffName;
    /** 新部门ID */
    @Alias("新部门ID")
    private Integer toDepartmentId;
    /** 新部门名称 */
    @Alias("新部门名称")
    private String toDepartmentName;
    /** 调拨原因 */
    @Alias("调拨原因")
    private String reason;
    /** 调拨状态 */
    @Alias("调拨状态")
    private String status; // 待审批、已审批、已调拨、已拒绝
    /** 审批人ID */
    @Alias("审批人ID")
    private Integer approverId;
    /** 审批人姓名 */
    @Alias("审批人姓名")
    private String approverName;
    /** 审批时间 */
    @Alias("审批时间")
    private String approveTime;
    /** 审批意见 */
    @Alias("审批意见")
    private String approveComment;
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

    public String getTransferTime() {
        return transferTime;
    }

    public void setTransferTime(String transferTime) {
        this.transferTime = transferTime;
    }

    public Integer getTransferStaffId() {
        return transferStaffId;
    }

    public void setTransferStaffId(Integer transferStaffId) {
        this.transferStaffId = transferStaffId;
    }

    public String getTransferStaffName() {
        return transferStaffName;
    }

    public void setTransferStaffName(String transferStaffName) {
        this.transferStaffName = transferStaffName;
    }

    public Integer getFromStaffId() {
        return fromStaffId;
    }

    public void setFromStaffId(Integer fromStaffId) {
        this.fromStaffId = fromStaffId;
    }

    public String getFromStaffName() {
        return fromStaffName;
    }

    public void setFromStaffName(String fromStaffName) {
        this.fromStaffName = fromStaffName;
    }

    public Integer getFromDepartmentId() {
        return fromDepartmentId;
    }

    public void setFromDepartmentId(Integer fromDepartmentId) {
        this.fromDepartmentId = fromDepartmentId;
    }

    public String getFromDepartmentName() {
        return fromDepartmentName;
    }

    public void setFromDepartmentName(String fromDepartmentName) {
        this.fromDepartmentName = fromDepartmentName;
    }

    public Integer getToStaffId() {
        return toStaffId;
    }

    public void setToStaffId(Integer toStaffId) {
        this.toStaffId = toStaffId;
    }

    public String getToStaffName() {
        return toStaffName;
    }

    public void setToStaffName(String toStaffName) {
        this.toStaffName = toStaffName;
    }

    public Integer getToDepartmentId() {
        return toDepartmentId;
    }

    public void setToDepartmentId(Integer toDepartmentId) {
        this.toDepartmentId = toDepartmentId;
    }

    public String getToDepartmentName() {
        return toDepartmentName;
    }

    public void setToDepartmentName(String toDepartmentName) {
        this.toDepartmentName = toDepartmentName;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Integer getApproverId() {
        return approverId;
    }

    public void setApproverId(Integer approverId) {
        this.approverId = approverId;
    }

    public String getApproverName() {
        return approverName;
    }

    public void setApproverName(String approverName) {
        this.approverName = approverName;
    }

    public String getApproveTime() {
        return approveTime;
    }

    public void setApproveTime(String approveTime) {
        this.approveTime = approveTime;
    }

    public String getApproveComment() {
        return approveComment;
    }

    public void setApproveComment(String approveComment) {
        this.approveComment = approveComment;
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
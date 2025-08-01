package com.example.entity;


import java.util.Date;

/**
 * 资产报修
*/
public class AssetsRepair {
//    /** ID */
//    private Integer id;
//    /** 资产领用ID */
//    private Integer receiveId;
//    /** 维修状态 */
//    private String status;
//    /** 报修理由 */
//    private String reason;
//    /** 报修日期 */
//    private String date;
//    /** 报修人 */
//    private Integer staffId;
//    private String assetsName;
//    private String staffName;

    private Integer id;                   // ID
    private Integer assetsId;             // 资产ID
    private String assetsName;            // 资产名称
    private String assetsCode;            // 资产编号
    private Integer receiveId;            // 资产领用ID
    private String status;                // 维修状态(处理中、已完成)
    private String reason;                // 报修理由
    private String faultDesc;             // 故障描述
    private String repairResult;          // 维修结果
    private Date completeTime;            // 完成时间
    private String date;                  // 报修日期
    private Integer staffId;              // 报修人
    private String staffName;             // 报修人姓名

    public String getStaffName() {
        return staffName;
    }

    public void setStaffName(String staffName) {
        this.staffName = staffName;
    }

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

    public String getAssetsCode() {
        return assetsCode;
    }

    public void setAssetsCode(String assetsCode) {
        this.assetsCode = assetsCode;
    }

    public Integer getReceiveId() {
        return receiveId;
    }

    public void setReceiveId(Integer receiveId) {
        this.receiveId = receiveId;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public String getFaultDesc() {
        return faultDesc;
    }

    public void setFaultDesc(String faultDesc) {
        this.faultDesc = faultDesc;
    }

    public String getRepairResult() {
        return repairResult;
    }

    public void setRepairResult(String repairResult) {
        this.repairResult = repairResult;
    }

    public Date getCompleteTime() {
        return completeTime;
    }

    public void setCompleteTime(Date completeTime) {
        this.completeTime = completeTime;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public Integer getStaffId() {
        return staffId;
    }

    public void setStaffId(Integer staffId) {
        this.staffId = staffId;
    }
}
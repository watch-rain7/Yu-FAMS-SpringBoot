package com.example.entity;


/**
 * 资产入库
*/
public class AssetsIn {
    /** ID */
    private Integer id;
    /** 资产ID */
    private Integer assetsId;
    /** 入库数量 */
    private Integer num;
    /** 操作人ID */
    private Integer staffId;
    /** 入库日期 */
    private String date;
    /** 备注 */
    private String comment;
    /** 审核状态 */
    private String status;

    private String assetsName;
    private String assetsNo;
    private String staffName;

    public String getAssetsNo() {
        return assetsNo;
    }

    public void setAssetsNo(String assetsNo) {
        this.assetsNo = assetsNo;
    }

    public String getStaffName() {
        return staffName;
    }

    public void setStaffName(String staffName) {
        this.staffName = staffName;
    }

    public String getAssetsName() {
        return assetsName;
    }

    public void setAssetsName(String assetsName) {
        this.assetsName = assetsName;
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

    public Integer getNum() {
        return num;
    }

    public void setNum(Integer num) {
        this.num = num;
    }

    public Integer getStaffId() {
        return staffId;
    }

    public void setStaffId(Integer staffId) {
        this.staffId = staffId;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

}
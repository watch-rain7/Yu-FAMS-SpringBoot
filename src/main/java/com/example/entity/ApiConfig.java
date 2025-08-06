package com.example.entity;

import cn.hutool.core.annotation.Alias;

/**
 * 接口配置信息
 */
public class ApiConfig {
    /** ID */
    @Alias("ID")
    private Integer id;
    /** 接口名称 */
    @Alias("接口名称")
    private String name;
    /** 接口描述 */
    @Alias("接口描述")
    private String description;
    /** 接口URL */
    @Alias("接口URL")
    private String url;
    /** 请求方法 */
    @Alias("请求方法")
    private String method; // GET、POST、PUT、DELETE
    /** 请求头 */
    @Alias("请求头")
    private String headers;
    /** 请求参数 */
    @Alias("请求参数")
    private String parameters;
    /** 响应格式 */
    @Alias("响应格式")
    private String responseFormat; // JSON、XML、TEXT
    /** 接口状态 */
    @Alias("接口状态")
    private String status; // 启用、禁用
    /** 接口类型 */
    @Alias("接口类型")
    private String type; // 内部接口、外部接口
    /** 认证方式 */
    @Alias("认证方式")
    private String authType; // 无认证、Token、OAuth2、API Key
    /** 认证信息 */
    @Alias("认证信息")
    private String authInfo;
    /** 超时时间(秒) */
    @Alias("超时时间")
    private Integer timeout;
    /** 重试次数 */
    @Alias("重试次数")
    private Integer retryCount;
    /** 创建人ID */
    @Alias("创建人ID")
    private Integer creatorId;
    /** 创建人姓名 */
    @Alias("创建人姓名")
    private String creatorName;
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

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getMethod() {
        return method;
    }

    public void setMethod(String method) {
        this.method = method;
    }

    public String getHeaders() {
        return headers;
    }

    public void setHeaders(String headers) {
        this.headers = headers;
    }

    public String getParameters() {
        return parameters;
    }

    public void setParameters(String parameters) {
        this.parameters = parameters;
    }

    public String getResponseFormat() {
        return responseFormat;
    }

    public void setResponseFormat(String responseFormat) {
        this.responseFormat = responseFormat;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getAuthType() {
        return authType;
    }

    public void setAuthType(String authType) {
        this.authType = authType;
    }

    public String getAuthInfo() {
        return authInfo;
    }

    public void setAuthInfo(String authInfo) {
        this.authInfo = authInfo;
    }

    public Integer getTimeout() {
        return timeout;
    }

    public void setTimeout(Integer timeout) {
        this.timeout = timeout;
    }

    public Integer getRetryCount() {
        return retryCount;
    }

    public void setRetryCount(Integer retryCount) {
        this.retryCount = retryCount;
    }

    public Integer getCreatorId() {
        return creatorId;
    }

    public void setCreatorId(Integer creatorId) {
        this.creatorId = creatorId;
    }

    public String getCreatorName() {
        return creatorName;
    }

    public void setCreatorName(String creatorName) {
        this.creatorName = creatorName;
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
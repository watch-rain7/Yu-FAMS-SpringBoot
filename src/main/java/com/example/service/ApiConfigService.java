package com.example.service;

import com.example.entity.ApiConfig;
import com.github.pagehelper.PageInfo;

import java.util.List;

public interface ApiConfigService {

    /**
     * 新增
     */
    void add(ApiConfig apiConfig);

    /**
     * 删除
     */
    void deleteById(Integer id);

    /**
     * 批量删除
     */
    void deleteBatch(List<Integer> ids);

    /**
     * 修改
     */
    void updateById(ApiConfig apiConfig);

    /**
     * 根据ID查询
     */
    ApiConfig selectById(Integer id);

    /**
     * 查询所有
     */
    List<ApiConfig> selectAll(ApiConfig apiConfig);

    /**
     * 分页查询
     */
    PageInfo<ApiConfig> selectPage(ApiConfig apiConfig, Integer pageNum, Integer pageSize);

    /**
     * 根据状态查询
     */
    List<ApiConfig> selectByStatus(String status);

    /**
     * 根据类型查询
     */
    List<ApiConfig> selectByType(String type);

    /**
     * 测试接口
     */
    String testApi(Integer id);
} 
package com.example.mapper;

import com.example.entity.ApiConfig;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * 接口配置数据访问层
 */
@Mapper
public interface ApiConfigMapper {

    /**
     * 新增
     */
    void insert(ApiConfig apiConfig);

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
    List<ApiConfig> selectPage(ApiConfig apiConfig);

    /**
     * 根据状态查询
     */
    List<ApiConfig> selectByStatus(String status);

    /**
     * 根据类型查询
     */
    List<ApiConfig> selectByType(String type);
} 
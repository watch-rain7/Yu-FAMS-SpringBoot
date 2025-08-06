package com.example.service;

import com.example.entity.AssetsDetail;
import com.github.pagehelper.PageInfo;

import java.util.List;

public interface AssetsDetailService {

    /**
     * 新增
     */
    void add(AssetsDetail assetsDetail);

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
    void updateById(AssetsDetail assetsDetail);

    /**
     * 根据ID查询
     */
    AssetsDetail selectById(Integer id);

    /**
     * 查询所有
     */
    List<AssetsDetail> selectAll(AssetsDetail assetsDetail);

    /**
     * 分页查询
     */
    PageInfo<AssetsDetail> selectPage(AssetsDetail assetsDetail, Integer pageNum, Integer pageSize);

    /**
     * 根据资产ID查询详情记录
     */
    List<AssetsDetail> selectByAssetsId(Integer assetsId);

    /**
     * 根据资产ID和操作类型查询记录
     */
    List<AssetsDetail> selectByAssetsIdAndType(Integer assetsId, String operationType);

    /**
     * 记录资产操作
     */
    void recordOperation(Integer assetsId, String operationType, String description, String comment);
} 
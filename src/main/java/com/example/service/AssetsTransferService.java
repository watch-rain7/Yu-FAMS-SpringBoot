package com.example.service;

import com.example.entity.AssetsTransfer;
import com.github.pagehelper.PageInfo;

import java.util.List;

public interface AssetsTransferService {

    /**
     * 新增
     */
    void add(AssetsTransfer assetsTransfer);

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
    void updateById(AssetsTransfer assetsTransfer);

    /**
     * 根据ID查询
     */
    AssetsTransfer selectById(Integer id);

    /**
     * 查询所有
     */
    List<AssetsTransfer> selectAll(AssetsTransfer assetsTransfer);

    /**
     * 分页查询
     */
    PageInfo<AssetsTransfer> selectPage(AssetsTransfer assetsTransfer, Integer pageNum, Integer pageSize);

    /**
     * 根据资产ID查询调拨记录
     */
    List<AssetsTransfer> selectByAssetsId(Integer assetsId);

    /**
     * 审批调拨申请
     */
    void approve(Integer id, String status, String approveComment);

    /**
     * 执行调拨
     */
    void executeTransfer(Integer id);
} 
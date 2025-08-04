package com.example.service;

import com.example.entity.Inventory;
import com.github.pagehelper.PageInfo;

import java.util.List;

public interface InventoryService {

    /**
     * 新增
     */
    void add(Inventory inventory);

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
    void updateById(Inventory inventory);

    /**
     * 根据ID查询
     */
    Inventory selectById(Integer id);

    /**
     * 查询所有
     */
    List<Inventory> selectAll(Inventory inventory);

    /**
     * 分页查询
     */
    PageInfo<Inventory> selectPage(Inventory inventory, Integer pageNum, Integer pageSize);

    /**
     * 查询最近的盘点记录
     */
    List<Inventory> getRecentInventory(Integer limit);
} 
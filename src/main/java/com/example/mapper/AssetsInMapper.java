package com.example.mapper;

import com.example.entity.AssetsIn;
import java.util.List;

/**
 * 操作assets_in相关数据接口
 */
public interface AssetsInMapper {

    /**
     * 新增
     */
    int insert(AssetsIn assetsIn);

    /**
     * 删除
     */
    int deleteById(Integer id);

    /**
     * 修改
     */
    int updateById(AssetsIn assetsIn);

    /**
     * 根据ID查询
     */
    AssetsIn selectById(Integer id);

    /**
     * 查询所有
     */
    List<AssetsIn> selectAll(AssetsIn assetsIn);

}
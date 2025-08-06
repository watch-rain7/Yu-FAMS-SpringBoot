package com.example.mapper;

import com.example.entity.AssetsTransfer;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * 资产调拨数据访问层
 */
@Mapper
public interface AssetsTransferMapper {

    /**
     * 新增
     */
    void insert(AssetsTransfer assetsTransfer);

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
    List<AssetsTransfer> selectPage(AssetsTransfer assetsTransfer);

    /**
     * 根据资产ID查询调拨记录
     */
    List<AssetsTransfer> selectByAssetsId(Integer assetsId);
} 
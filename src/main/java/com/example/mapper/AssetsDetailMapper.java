package com.example.mapper;

import com.example.entity.AssetsDetail;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * 资产详情记录数据访问层
 */
@Mapper
public interface AssetsDetailMapper {

    /**
     * 新增
     */
    void insert(AssetsDetail assetsDetail);

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
    List<AssetsDetail> selectPage(AssetsDetail assetsDetail);

    /**
     * 根据资产ID查询详情记录
     */
    List<AssetsDetail> selectByAssetsId(Integer assetsId);

    /**
     * 根据资产ID和操作类型查询记录
     */
    List<AssetsDetail> selectByAssetsIdAndType(Integer assetsId, String operationType);
} 
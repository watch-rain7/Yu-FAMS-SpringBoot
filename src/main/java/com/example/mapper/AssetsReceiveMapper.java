package com.example.mapper;

import com.example.entity.AssetsReceive;
import java.util.List;

/**
 * 操作assets_receive相关数据接口
*/
public interface AssetsReceiveMapper {

    /**
      * 新增
    */
    int insert(AssetsReceive assetsReceive);

    /**
      * 删除
    */
    int deleteById(Integer id);

    /**
      * 修改
    */
    int updateById(AssetsReceive assetsReceive);

    /**
      * 根据ID查询
    */
    AssetsReceive selectById(Integer id);

    /**
      * 查询所有
    */
    List<AssetsReceive> selectAll(AssetsReceive assetsReceive);

}
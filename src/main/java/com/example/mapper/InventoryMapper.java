package com.example.mapper;

import com.example.entity.Inventory;
import java.util.List;

/**
 * 操作inventory相关数据接口
*/
public interface InventoryMapper {

    /**
      * 新增
    */
    int insert(Inventory inventory);

    /**
      * 删除
    */
    int deleteById(Integer id);

    /**
      * 修改
    */
    int updateById(Inventory inventory);

    /**
      * 根据ID查询
    */
    Inventory selectById(Integer id);

    /**
      * 查询所有
    */
    List<Inventory> selectAll(Inventory inventory);

    /**
      * 统计盘点数据
    */
    List<Inventory> selectStats();
}
package com.example.mapper;

import com.example.entity.AssetsRepair;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * 操作assets_repair相关数据接口
*/
public interface AssetsRepairMapper {

    /**
      * 新增
    */
    int insert(AssetsRepair assetsRepair);

    /**
      * 删除
    */
    int deleteById(Integer id);

    /**
      * 修改
    */
    int updateById(AssetsRepair assetsRepair);

    /**
      * 根据ID查询
    */
    AssetsRepair selectById(Integer id);

    /**
      * 查询所有
    */
    List<AssetsRepair> selectAll(AssetsRepair assetsRepair);

    AssetsRepair selectInProcessRepair(Integer receiveId);
    List<AssetsRepair> selectRecent(int limit);
    
    /**
     * 根据receiveId查询资产信息
     */
    AssetsRepair selectAssetByReceiveId(Integer receiveId);

////    @Select("select * from assets_repair where receive_id=#{receive_id} and status='待审核'or status='维修中'")
//    AssetsRepair selectInProcessRepair(Integer receiveId);
//
//
////    @Select("SELECT ar.*, a.name AS asset_name, a.no AS asset_code " +
////            "FROM assets_repair ar " +
////            "JOIN assets a ON ar.asset_id = a.id " +
////            "ORDER BY ar.date DESC LIMIT #{limit}")
//    List<AssetsRepair> selectRecent(int limit);


}
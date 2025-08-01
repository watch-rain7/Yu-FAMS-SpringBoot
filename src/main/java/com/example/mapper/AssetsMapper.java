package com.example.mapper;

import com.example.entity.Assets;
import com.example.entity.Category;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * 操作assets相关数据接口
 */
public interface AssetsMapper {

    /**
     * 新增
     */
    int insert(Assets assets);

    /**
     * 删除
     */
    int deleteById(Integer id);

    /**
     * 修改
     */
    int updateById(Assets assets);

    /**
     * 根据ID查询
     */
    Assets selectById(Integer id);

    /**
     * 查询所有
     */
    List<Assets> selectAll(Assets assets);

    @Select("select count(*) from assets where category = #{category}")
    int selectCountByCategory(String category);
}
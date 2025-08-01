package com.example.mapper;

import com.example.entity.Staff;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * 操作staff相关数据接口
*/
public interface StaffMapper {

    /**
      * 新增
    */
    int insert(Staff staff);

    /**
      * 删除
    */
    int deleteById(Integer id);

    /**
      * 修改
    */
    int updateById(Staff staff);

    /**
      * 根据ID查询
    */
    Staff selectById(Integer id);

    /**
      * 查询所有
    */
    List<Staff> selectAll(Staff staff);

    @Select("select * from staff where username = #{username}")
    Staff selectByUsername(String username);
}
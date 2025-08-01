package com.example.mapper;


import com.example.entity.Assets;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface AssetMapper {

    @Select("SELECT * FROM assets ORDER BY id DESC LIMIT #{limit}")
    List<Assets> selectRecent(int limit);
}

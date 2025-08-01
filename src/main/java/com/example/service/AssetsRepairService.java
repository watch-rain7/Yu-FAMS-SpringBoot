package com.example.service;

import cn.hutool.core.date.DateUtil;
import cn.hutool.core.util.ObjectUtil;
import com.example.common.enums.ResultCodeEnum;
import com.example.common.enums.RoleEnum;
import com.example.entity.Account;
import com.example.entity.AssetsRepair;
import com.example.exception.CustomException;
import com.example.utils.TokenUtils;
import com.github.pagehelper.PageInfo;
import java.util.List;

/**
 * 资产报修业务处理接口
 **/
public interface AssetsRepairService {

    void add(AssetsRepair assetsRepair);

    void deleteById(Integer id);

    void deleteBatch(List<Integer> ids);

    void updateById(AssetsRepair assetsRepair);

    AssetsRepair selectById(Integer id);

    List<AssetsRepair> selectAll(AssetsRepair assetsRepair);

    PageInfo<AssetsRepair> selectPage(AssetsRepair assetsRepair, Integer pageNum, Integer pageSize);

    List<AssetsRepair> getRecentRepairs();
}
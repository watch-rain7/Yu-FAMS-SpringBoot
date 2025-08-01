package com.example.service.impl;

import cn.hutool.core.date.DateUtil;
import cn.hutool.core.util.ObjectUtil;
import com.example.common.enums.ResultCodeEnum;
import com.example.common.enums.RoleEnum;
import com.example.entity.Account;
import com.example.entity.AssetsRepair;
import com.example.exception.CustomException;
import com.example.mapper.AssetsRepairMapper;
import com.example.service.AssetsRepairService;
import com.example.utils.TokenUtils;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import java.util.List;
import java.util.Date;

/**
 * 资产报修业务处理实现
 **/
@Service
public class AssetsRepairServiceImpl implements AssetsRepairService {

    @Resource
    private AssetsRepairMapper assetsRepairMapper;

    @Override
    public void add(AssetsRepair assetsRepair) {
        Account currentUser = TokenUtils.getCurrentUser();
        
        // 如果是从员工端申请维修，需要根据receiveId获取资产信息
        if (assetsRepair.getReceiveId() != null) {
            // 根据receiveId查询资产信息
            AssetsRepair assetInfo = assetsRepairMapper.selectAssetByReceiveId(assetsRepair.getReceiveId());
            if (assetInfo != null) {
                assetsRepair.setAssetsId(assetInfo.getAssetsId());
                assetsRepair.setAssetsName(assetInfo.getAssetsName());
                assetsRepair.setAssetsCode(assetInfo.getAssetsCode());
            } else {
                // 如果查询不到，设置默认值
                assetsRepair.setAssetsId(1);
                assetsRepair.setAssetsName("待确认");
                assetsRepair.setAssetsCode("待确认");
            }
        }
        
        // 校验当前是否有同样的报修单
        AssetsRepair dbRepair = assetsRepairMapper.selectInProcessRepair(assetsRepair.getReceiveId());
        if (ObjectUtil.isNotNull(dbRepair)) {
            throw new CustomException(ResultCodeEnum.REPAIR_ERROR);
        }
        assetsRepair.setStaffId(currentUser.getId());
        assetsRepair.setStatus("处理中");
        assetsRepair.setDate(DateUtil.now());
        assetsRepairMapper.insert(assetsRepair);
    }

    @Override
    public void deleteById(Integer id) {
        assetsRepairMapper.deleteById(id);
    }

    @Override
    public void deleteBatch(List<Integer> ids) {
        for (Integer id : ids) {
            assetsRepairMapper.deleteById(id);
        }
    }

    @Override
    public void updateById(AssetsRepair assetsRepair) {
        // 如果状态变为已完成，设置完成时间
        if ("已完成".equals(assetsRepair.getStatus()) && assetsRepair.getCompleteTime() == null) {
            assetsRepair.setCompleteTime(new Date());
        }
        assetsRepairMapper.updateById(assetsRepair);
    }

    @Override
    public AssetsRepair selectById(Integer id) {
        return assetsRepairMapper.selectById(id);
    }

    @Override
    public List<AssetsRepair> selectAll(AssetsRepair assetsRepair) {
        return assetsRepairMapper.selectAll(assetsRepair);
    }

    @Override
    public PageInfo<AssetsRepair> selectPage(AssetsRepair assetsRepair, Integer pageNum, Integer pageSize) {
        Account currentUser = TokenUtils.getCurrentUser();
        if (RoleEnum.STAFF.name().equals(currentUser.getRole())) {
            assetsRepair.setStaffId(currentUser.getId());
        }
        PageHelper.startPage(pageNum, pageSize);
        List<AssetsRepair> list = assetsRepairMapper.selectAll(assetsRepair);
        return PageInfo.of(list);
    }


    @Override
    public List<AssetsRepair> getRecentRepairs() {
        return assetsRepairMapper.selectRecent(10);
    }
}
package com.example.service;

import cn.hutool.core.date.DateUtil;
import cn.hutool.core.util.ObjectUtil;
import com.example.common.enums.ResultCodeEnum;
import com.example.common.enums.RoleEnum;
import com.example.entity.Account;
import com.example.entity.Assets;
import com.example.entity.AssetsReceive;
import com.example.entity.AssetsRepair;
import com.example.exception.CustomException;
import com.example.mapper.AssetsReceiveMapper;
import com.example.mapper.AssetsRepairMapper;
import com.example.utils.TokenUtils;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import javax.annotation.Resource;
import org.springframework.stereotype.Service;
import java.util.List;

/**
 * 资产领用业务处理
 **/
@Service
public class AssetsReceiveService {

    @Resource
    private AssetsReceiveMapper assetsReceiveMapper;
    @Resource
    private AssetsService assetsService;
    @Resource
    private AssetsRepairMapper assetsRepairMapper;
    /**
     * 新增
     */
    public void add(AssetsReceive assetsReceive) {
        Integer assetsId = assetsReceive.getAssetsId();
        Assets assets = assetsService.selectById(assetsId);
        if (assets.getNum() <= 0) {
            throw new CustomException(ResultCodeEnum.ASSETS_NUM_ERROR);
        }
        Account currentUser = TokenUtils.getCurrentUser();
        assetsReceive.setStaffId(currentUser.getId());  // 设置当前正在操作人的ID  表示这个人是领用人
        assetsReceive.setDate(DateUtil.now());
        assetsReceive.setStatus("待审核");
        assetsReceive.setReturnStatus("未归还");
        assetsReceiveMapper.insert(assetsReceive);
    }
    /**
     * 删除
     */
    public void deleteById(Integer id) {
        assetsReceiveMapper.deleteById(id);
    }

    /**
     * 批量删除
     */
    public void deleteBatch(List<Integer> ids) {
        for (Integer id : ids) {
            assetsReceiveMapper.deleteById(id);
        }
    }


    /**
     * 修改
     */
    public void updateById(AssetsReceive assetsReceive) {
        Account currentUser = TokenUtils.getCurrentUser();
        // 管理员审核通过的时候   库存的数量-1
        if (RoleEnum.ADMIN.name().equals(currentUser.getRole()) && "通过".equals(assetsReceive.getStatus())) {
            Assets assets = assetsService.selectById(assetsReceive.getAssetsId());
            assets.setNum(assets.getNum() - 1);
            assetsService.updateById(assets);
        }

        // 员工发起归还的时候   库存的数量+1
        if (RoleEnum.STAFF.name().equals(currentUser.getRole()) && "已归还".equals(assetsReceive.getReturnStatus())) {
            // 检测是否存在维修中的工单
            AssetsRepair assetsRepair = assetsRepairMapper.selectInProcessRepair(assetsReceive.getId());
            if (ObjectUtil.isNotNull(assetsRepair)) {
                throw new CustomException(ResultCodeEnum.REPAIR_ERROR);
            }
            Assets assets = assetsService.selectById(assetsReceive.getAssetsId());
            assets.setNum(assets.getNum() + 1);
            assetsService.updateById(assets);
        }
        assetsReceiveMapper.updateById(assetsReceive);
    }
    /**
     * 根据ID查询
     */
    public AssetsReceive selectById(Integer id) {
        return assetsReceiveMapper.selectById(id);
    }

    /**
     * 查询所有
     */
    public List<AssetsReceive> selectAll(AssetsReceive assetsReceive) {
        return assetsReceiveMapper.selectAll(assetsReceive);
    }


    /**
     * 分页查询
     */
    public PageInfo<AssetsReceive> selectPage(AssetsReceive assetsReceive, Integer pageNum, Integer pageSize) {
        Account currentUser = TokenUtils.getCurrentUser();
        if (RoleEnum.STAFF.name().equals(currentUser.getRole())) {
            assetsReceive.setStaffId(currentUser.getId());
        }
        PageHelper.startPage(pageNum, pageSize);
        List<AssetsReceive> list = assetsReceiveMapper.selectAll(assetsReceive);
        return PageInfo.of(list);
    }

}
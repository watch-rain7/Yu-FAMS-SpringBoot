package com.example.service;

import cn.hutool.core.date.DateUtil;
import com.example.common.enums.RoleEnum;
import com.example.entity.Account;
import com.example.entity.Assets;
import com.example.entity.AssetsIn;
import com.example.mapper.AssetsInMapper;
import com.example.utils.TokenUtils;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import javax.annotation.Resource;
import org.springframework.stereotype.Service;
import java.util.List;

/**
 * 资产入库业务处理
 **/
@Service
public class AssetsInService {

    @Resource
    private AssetsInMapper assetsInMapper;
    @Resource
    AssetsService assetsService;

    /**
     * 新增
     */
    public void add(AssetsIn assetsIn) {
        Account currentUser = TokenUtils.getCurrentUser();
        assetsIn.setStaffId(currentUser.getId());
        assetsIn.setStatus("待审核");
        assetsIn.setDate(DateUtil.now());
        assetsInMapper.insert(assetsIn);
    }

    /**
     * 删除
     */
    public void deleteById(Integer id) {
        assetsInMapper.deleteById(id);
    }

    /**
     * 批量删除
     */
    public void deleteBatch(List<Integer> ids) {
        for (Integer id : ids) {
            assetsInMapper.deleteById(id);
        }
    }

    /**
     * 修改
     */
    public void updateById(AssetsIn assetsIn) {
        Account currentUser = TokenUtils.getCurrentUser();
        if (RoleEnum.ADMIN.name().equals(currentUser.getRole()) && "通过".equals(assetsIn.getStatus())) {
            Assets assets = assetsService.selectById(assetsIn.getAssetsId());
            assets.setNum(assets.getNum() + assetsIn.getNum());
            assetsService.updateById(assets);
        }
        assetsInMapper.updateById(assetsIn);
    }

    /**
     * 根据ID查询
     */
    public AssetsIn selectById(Integer id) {
        return assetsInMapper.selectById(id);
    }

    /**
     * 查询所有
     */
    public List<AssetsIn> selectAll(AssetsIn assetsIn) {
        return assetsInMapper.selectAll(assetsIn);
    }

    /**
     * 分页查询
     */
    public PageInfo<AssetsIn> selectPage(AssetsIn assetsIn, Integer pageNum, Integer pageSize) {
        Account currentUser = TokenUtils.getCurrentUser();
        if (RoleEnum.STAFF.name().equals(currentUser.getRole())) {
            assetsIn.setStaffId(currentUser.getId());
        }
        PageHelper.startPage(pageNum, pageSize);
        List<AssetsIn> list = assetsInMapper.selectAll(assetsIn);
        return PageInfo.of(list);
    }

}
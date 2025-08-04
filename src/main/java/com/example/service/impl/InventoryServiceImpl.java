package com.example.service.impl;

import cn.hutool.core.date.DateUtil;
import com.example.entity.Account;
import com.example.entity.Assets;
import com.example.entity.Inventory;
import com.example.mapper.AssetsMapper;
import com.example.mapper.InventoryMapper;
import com.example.service.InventoryService;
import com.example.utils.TokenUtils;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * 资产盘点业务处理实现
 **/
@Service
public class InventoryServiceImpl implements InventoryService {

    @Resource
    private InventoryMapper inventoryMapper;
    
    @Resource
    private AssetsMapper assetsMapper;

    @Override
    public void add(Inventory inventory) {
        Account currentUser = TokenUtils.getCurrentUser();
        
        // 验证资产是否存在
        if (inventory.getAssetsId() != null) {
            Assets assets = assetsMapper.selectById(inventory.getAssetsId());
            if (assets == null) {
                throw new RuntimeException("资产不存在");
            }
            // 设置资产信息
            inventory.setAssetsName(assets.getName());
            inventory.setAssetsNo(assets.getNo());
        }
        
        // 设置盘点人信息
        inventory.setStaffId(currentUser.getId());
        inventory.setStaffName(currentUser.getName());
        
        // 设置盘点时间
        if (inventory.getInventoryTime() == null) {
            inventory.setInventoryTime(DateUtil.now());
        }
        
        // 设置默认状态
        if (inventory.getStatus() == null) {
            inventory.setStatus("待盘点");
        }
        
        // 设置默认结果
        if (inventory.getResult() == null) {
            inventory.setResult("正常");
        }
        
        inventoryMapper.insert(inventory);
    }

    @Override
    public void deleteById(Integer id) {
        inventoryMapper.deleteById(id);
    }

    @Override
    public void deleteBatch(List<Integer> ids) {
        for (Integer id : ids) {
            inventoryMapper.deleteById(id);
        }
    }

    @Override
    public void updateById(Inventory inventory) {
        // 如果修改了资产ID，需要重新获取资产信息
        if (inventory.getAssetsId() != null) {
            Assets assets = assetsMapper.selectById(inventory.getAssetsId());
            if (assets != null) {
                inventory.setAssetsName(assets.getName());
                inventory.setAssetsNo(assets.getNo());
            }
        }
        
        inventoryMapper.updateById(inventory);
    }

    @Override
    public Inventory selectById(Integer id) {
        return inventoryMapper.selectById(id);
    }

    @Override
    public List<Inventory> selectAll(Inventory inventory) {
        return inventoryMapper.selectAll(inventory);
    }

    @Override
    public PageInfo<Inventory> selectPage(Inventory inventory, Integer pageNum, Integer pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        List<Inventory> list = inventoryMapper.selectAll(inventory);
        return PageInfo.of(list);
    }

    @Override
    public List<Inventory> getRecentInventory(Integer limit) {
        return inventoryMapper.selectRecent(limit);
    }
} 
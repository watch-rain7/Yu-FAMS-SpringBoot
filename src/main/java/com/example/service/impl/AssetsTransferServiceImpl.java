package com.example.service.impl;

import cn.hutool.core.date.DateUtil;
import com.example.entity.Account;
import com.example.entity.Assets;
import com.example.entity.AssetsDetail;
import com.example.entity.AssetsTransfer;
import com.example.mapper.AssetsDetailMapper;
import com.example.mapper.AssetsMapper;
import com.example.mapper.AssetsTransferMapper;
import com.example.service.AssetsTransferService;
import com.example.utils.TokenUtils;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

/**
 * 资产调拨业务处理实现
 **/
@Service
public class AssetsTransferServiceImpl implements AssetsTransferService {

    @Resource
    private AssetsTransferMapper assetsTransferMapper;
    
    @Resource
    private AssetsMapper assetsMapper;
    
    @Resource
    private AssetsDetailMapper assetsDetailMapper;

    @Override
    public void add(AssetsTransfer assetsTransfer) {
        Account currentUser = TokenUtils.getCurrentUser();
        
        // 验证资产是否存在
        if (assetsTransfer.getAssetsId() != null) {
            Assets assets = assetsMapper.selectById(assetsTransfer.getAssetsId());
            if (assets == null) {
                throw new RuntimeException("资产不存在");
            }
            // 设置资产信息
            assetsTransfer.setAssetsName(assets.getName());
            assetsTransfer.setAssetsNo(assets.getNo());
        }
        
        // 设置调拨人信息
        assetsTransfer.setTransferStaffId(currentUser.getId());
        assetsTransfer.setTransferStaffName(currentUser.getName());
        
        // 设置调拨时间
        if (assetsTransfer.getTransferTime() == null) {
            assetsTransfer.setTransferTime(DateUtil.now());
        }
        
        // 设置默认状态
        if (assetsTransfer.getStatus() == null) {
            assetsTransfer.setStatus("待审批");
        }
        
        assetsTransferMapper.insert(assetsTransfer);
    }

    @Override
    public void deleteById(Integer id) {
        assetsTransferMapper.deleteById(id);
    }

    @Override
    public void deleteBatch(List<Integer> ids) {
        for (Integer id : ids) {
            assetsTransferMapper.deleteById(id);
        }
    }

    @Override
    public void updateById(AssetsTransfer assetsTransfer) {
        // 如果修改了资产ID，需要重新获取资产信息
        if (assetsTransfer.getAssetsId() != null) {
            Assets assets = assetsMapper.selectById(assetsTransfer.getAssetsId());
            if (assets != null) {
                assetsTransfer.setAssetsName(assets.getName());
                assetsTransfer.setAssetsNo(assets.getNo());
            }
        }
        
        assetsTransferMapper.updateById(assetsTransfer);
    }

    @Override
    public AssetsTransfer selectById(Integer id) {
        return assetsTransferMapper.selectById(id);
    }

    @Override
    public List<AssetsTransfer> selectAll(AssetsTransfer assetsTransfer) {
        return assetsTransferMapper.selectAll(assetsTransfer);
    }

    @Override
    public PageInfo<AssetsTransfer> selectPage(AssetsTransfer assetsTransfer, Integer pageNum, Integer pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        List<AssetsTransfer> list = assetsTransferMapper.selectAll(assetsTransfer);
        return PageInfo.of(list);
    }

    @Override
    public List<AssetsTransfer> selectByAssetsId(Integer assetsId) {
        return assetsTransferMapper.selectByAssetsId(assetsId);
    }

    @Override
    @Transactional
    public void approve(Integer id, String status, String approveComment) {
        Account currentUser = TokenUtils.getCurrentUser();
        
        AssetsTransfer transfer = assetsTransferMapper.selectById(id);
        if (transfer == null) {
            throw new RuntimeException("调拨记录不存在");
        }
        
        transfer.setStatus(status);
        transfer.setApproverId(currentUser.getId());
        transfer.setApproverName(currentUser.getName());
        transfer.setApproveTime(DateUtil.now());
        transfer.setApproveComment(approveComment);
        
        assetsTransferMapper.updateById(transfer);
    }

    @Override
    @Transactional
    public void executeTransfer(Integer id) {
        Account currentUser = TokenUtils.getCurrentUser();
        
        AssetsTransfer transfer = assetsTransferMapper.selectById(id);
        if (transfer == null) {
            throw new RuntimeException("调拨记录不存在");
        }
        
        if (!"已审批".equals(transfer.getStatus())) {
            throw new RuntimeException("只有已审批的调拨申请才能执行");
        }
        
        // 更新资产信息
        Assets assets = assetsMapper.selectById(transfer.getAssetsId());
        if (assets == null) {
            throw new RuntimeException("资产不存在");
        }
        
        // 记录调拨前的状态
        AssetsDetail detail = new AssetsDetail();
        detail.setAssetsId(assets.getId());
        detail.setAssetsName(assets.getName());
        detail.setAssetsNo(assets.getNo());
        detail.setOperationType("调拨");
        detail.setOperationTime(DateUtil.now());
        detail.setOperatorId(currentUser.getId());
        detail.setOperatorName(currentUser.getName());
        detail.setBeforeStatus(assets.getStatus());
        detail.setAfterStatus(assets.getStatus());
        detail.setBeforeStaffId(assets.getStaffId());
        detail.setBeforeStaffName(assets.getStaffName());
        detail.setAfterStaffId(transfer.getToStaffId());
        detail.setAfterStaffName(transfer.getToStaffName());
        detail.setBeforeDepartmentId(assets.getDepartmentId());
        detail.setBeforeDepartmentName(assets.getDepartmentName());
        detail.setAfterDepartmentId(transfer.getToDepartmentId());
        detail.setAfterDepartmentName(transfer.getToDepartmentName());
        detail.setBeforeLocation(assets.getLocation());
        detail.setAfterLocation(assets.getLocation());
        detail.setDescription("资产调拨：" + transfer.getFromStaffName() + " → " + transfer.getToStaffName());
        detail.setComment(transfer.getReason());
        
        assetsDetailMapper.insert(detail);
        
        // 更新资产信息
        assets.setStaffId(transfer.getToStaffId());
        assets.setStaffName(transfer.getToStaffName());
        assets.setDepartmentId(transfer.getToDepartmentId());
        assets.setDepartmentName(transfer.getToDepartmentName());
        
        assetsMapper.updateById(assets);
        
        // 更新调拨状态
        transfer.setStatus("已调拨");
        assetsTransferMapper.updateById(transfer);
    }
} 
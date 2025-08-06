package com.example.service.impl;

import cn.hutool.core.date.DateUtil;
import com.example.entity.Account;
import com.example.entity.Assets;
import com.example.entity.AssetsDetail;
import com.example.mapper.AssetsDetailMapper;
import com.example.mapper.AssetsMapper;
import com.example.service.AssetsDetailService;
import com.example.utils.TokenUtils;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * 资产详情记录业务处理实现
 **/
@Service
public class AssetsDetailServiceImpl implements AssetsDetailService {

    @Resource
    private AssetsDetailMapper assetsDetailMapper;
    
    @Resource
    private AssetsMapper assetsMapper;

    @Override
    public void add(AssetsDetail assetsDetail) {
        Account currentUser = TokenUtils.getCurrentUser();
        
        // 验证资产是否存在
        if (assetsDetail.getAssetsId() != null) {
            Assets assets = assetsMapper.selectById(assetsDetail.getAssetsId());
            if (assets == null) {
                throw new RuntimeException("资产不存在");
            }
            // 设置资产信息
            assetsDetail.setAssetsName(assets.getName());
            assetsDetail.setAssetsNo(assets.getNo());
        }
        
        // 设置操作人信息
        assetsDetail.setOperatorId(currentUser.getId());
        assetsDetail.setOperatorName(currentUser.getName());
        
        // 设置操作时间
        if (assetsDetail.getOperationTime() == null) {
            assetsDetail.setOperationTime(DateUtil.now());
        }
        
        assetsDetailMapper.insert(assetsDetail);
    }

    @Override
    public void deleteById(Integer id) {
        assetsDetailMapper.deleteById(id);
    }

    @Override
    public void deleteBatch(List<Integer> ids) {
        for (Integer id : ids) {
            assetsDetailMapper.deleteById(id);
        }
    }

    @Override
    public void updateById(AssetsDetail assetsDetail) {
        // 如果修改了资产ID，需要重新获取资产信息
        if (assetsDetail.getAssetsId() != null) {
            Assets assets = assetsMapper.selectById(assetsDetail.getAssetsId());
            if (assets != null) {
                assetsDetail.setAssetsName(assets.getName());
                assetsDetail.setAssetsNo(assets.getNo());
            }
        }
        
        assetsDetailMapper.updateById(assetsDetail);
    }

    @Override
    public AssetsDetail selectById(Integer id) {
        return assetsDetailMapper.selectById(id);
    }

    @Override
    public List<AssetsDetail> selectAll(AssetsDetail assetsDetail) {
        return assetsDetailMapper.selectAll(assetsDetail);
    }

    @Override
    public PageInfo<AssetsDetail> selectPage(AssetsDetail assetsDetail, Integer pageNum, Integer pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        List<AssetsDetail> list = assetsDetailMapper.selectAll(assetsDetail);
        return PageInfo.of(list);
    }

    @Override
    public List<AssetsDetail> selectByAssetsId(Integer assetsId) {
        return assetsDetailMapper.selectByAssetsId(assetsId);
    }

    @Override
    public List<AssetsDetail> selectByAssetsIdAndType(Integer assetsId, String operationType) {
        return assetsDetailMapper.selectByAssetsIdAndType(assetsId, operationType);
    }

    @Override
    public void recordOperation(Integer assetsId, String operationType, String description, String comment) {
        Account currentUser = TokenUtils.getCurrentUser();
        
        Assets assets = assetsMapper.selectById(assetsId);
        if (assets == null) {
            throw new RuntimeException("资产不存在");
        }
        
        AssetsDetail detail = new AssetsDetail();
        detail.setAssetsId(assets.getId());
        detail.setAssetsName(assets.getName());
        detail.setAssetsNo(assets.getNo());
        detail.setOperationType(operationType);
        detail.setOperationTime(DateUtil.now());
        detail.setOperatorId(currentUser.getId());
        detail.setOperatorName(currentUser.getName());
        detail.setBeforeStatus(assets.getStatus());
        detail.setAfterStatus(assets.getStatus());
        detail.setBeforeStaffId(assets.getStaffId());
        detail.setBeforeStaffName(assets.getStaffName());
        detail.setAfterStaffId(assets.getStaffId());
        detail.setAfterStaffName(assets.getStaffName());
        detail.setBeforeDepartmentId(assets.getDepartmentId());
        detail.setBeforeDepartmentName(assets.getDepartmentName());
        detail.setAfterDepartmentId(assets.getDepartmentId());
        detail.setAfterDepartmentName(assets.getDepartmentName());
        detail.setBeforeLocation(assets.getLocation());
        detail.setAfterLocation(assets.getLocation());
        detail.setDescription(description);
        detail.setComment(comment);
        
        assetsDetailMapper.insert(detail);
    }
} 
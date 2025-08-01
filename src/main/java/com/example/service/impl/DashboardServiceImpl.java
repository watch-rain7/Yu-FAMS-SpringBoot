package com.example.service.impl;


import com.example.dto.DashboardStats;
import com.example.dto.StatusDistribution;
import com.example.dto.RepairStats;
import com.example.dto.InventoryTrend;
import com.example.mapper.DashboardMapper;
import com.example.service.DashboardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.ArrayList;

@Service
public class DashboardServiceImpl implements DashboardService {

    @Autowired
    private DashboardMapper dashboardMapper;

    @Override
    public DashboardStats getDashboardStats() {
        return dashboardMapper.getDashboardStats();
    }

    @Override
    public List<StatusDistribution> getStatusDistribution() {
        return dashboardMapper.getStatusDistribution();
    }

    @Override
    public RepairStats getRepairStats() {
        List<Object> statsList = dashboardMapper.getRepairStats();
        RepairStats result = new RepairStats();
        
        if (statsList != null && !statsList.isEmpty()) {
            List<String> labels = new ArrayList<>();
            List<Integer> repairCounts = new ArrayList<>();
            List<Integer> completedCounts = new ArrayList<>();
            
            // 这里需要根据实际返回的数据结构进行处理
            // 暂时返回空数据，避免前端报错
            result.setLabels(labels);
            result.setRepairCounts(repairCounts);
            result.setCompletedCounts(completedCounts);
        }
        
        return result;
    }

    @Override
    public InventoryTrend getInventoryTrend() {
        return dashboardMapper.getInventoryTrend();
    }
}
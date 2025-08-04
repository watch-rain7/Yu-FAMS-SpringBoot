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
import java.util.Map;

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
        List<Map<String, Object>> statsList = dashboardMapper.getRepairStats();
        RepairStats result = new RepairStats();
        
        if (statsList != null && !statsList.isEmpty()) {
            List<String> labels = new ArrayList<>();
            List<Integer> repairCounts = new ArrayList<>();
            List<Integer> completedCounts = new ArrayList<>();
            
            for (Map<String, Object> item : statsList) {
                labels.add((String) item.get("date"));
                repairCounts.add(((Number) item.get("repairCount")).intValue());
                completedCounts.add(((Number) item.get("completedCount")).intValue());
            }
            
            result.setLabels(labels);
            result.setRepairCounts(repairCounts);
            result.setCompletedCounts(completedCounts);
        } else {
            // 如果没有数据，返回空列表避免前端报错
            result.setLabels(new ArrayList<>());
            result.setRepairCounts(new ArrayList<>());
            result.setCompletedCounts(new ArrayList<>());
        }
        
        return result;
    }

    @Override
    public InventoryTrend getInventoryTrend() {
        List<Map<String, Object>> trendList = dashboardMapper.getInventoryTrend();
        InventoryTrend result = new InventoryTrend();
        
        if (trendList != null && !trendList.isEmpty()) {
            List<String> labels = new ArrayList<>();
            List<Integer> values = new ArrayList<>();
            
            for (Map<String, Object> item : trendList) {
                labels.add((String) item.get("month"));
                values.add(((Number) item.get("count")).intValue());
            }
            
            result.setLabels(labels);
            result.setValues(values);
        } else {
            // 如果没有数据，返回空列表避免前端报错
            result.setLabels(new ArrayList<>());
            result.setValues(new ArrayList<>());
        }
        
        return result;
    }
}
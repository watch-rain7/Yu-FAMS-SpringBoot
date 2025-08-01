package com.example.service;

import com.example.dto.DashboardStats;
import com.example.dto.StatusDistribution;
import com.example.dto.RepairStats;
import com.example.dto.InventoryTrend;

import java.util.List;

public interface DashboardService {
    DashboardStats getDashboardStats();
    List<StatusDistribution> getStatusDistribution();
    RepairStats getRepairStats();
    InventoryTrend getInventoryTrend();
}
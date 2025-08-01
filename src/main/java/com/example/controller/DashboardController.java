package com.example.controller;

import com.example.common.Result;
import com.example.dto.DashboardStats;
import com.example.dto.StatusDistribution;
import com.example.dto.RepairStats;
import com.example.dto.InventoryTrend;
import com.example.service.DashboardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/dashboard")
public class DashboardController {

    @Autowired
    private DashboardService dashboardService;

    @GetMapping("/stats")
    public Result getDashboardStats() {
        return Result.success(dashboardService.getDashboardStats());
    }

    @GetMapping("/status-distribution")
    public Result getStatusDistribution() {
        return Result.success(dashboardService.getStatusDistribution());
    }

    @GetMapping("/repair-stats")
    public Result getRepairStats() {
        return Result.success(dashboardService.getRepairStats());
    }

    @GetMapping("/inventory-trend")
    public Result getInventoryTrend() {
        return Result.success(dashboardService.getInventoryTrend());
    }
}
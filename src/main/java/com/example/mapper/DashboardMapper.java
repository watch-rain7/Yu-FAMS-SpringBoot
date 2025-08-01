package com.example.mapper;

import com.example.dto.DashboardStats;
import com.example.dto.StatusDistribution;
import com.example.dto.RepairStats;
import com.example.dto.InventoryTrend;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.math.BigDecimal;
import java.util.List;

@Mapper
public interface DashboardMapper {

    @Select("SELECT " +
            "(SELECT COUNT(*) FROM assets) AS totalAssets, " +
            "(SELECT COUNT(*) FROM assets WHERE status = '维修中') AS underRepair, " +
            "(SELECT COALESCE(SUM(current_value), 0) FROM assets) AS totalValue, " +
            "(SELECT COUNT(*) FROM inventory WHERE inventory_time >= DATE_SUB(CURDATE(), INTERVAL 1 MONTH)) AS inventoryCount, " +
            "(SELECT COUNT(*) FROM inventory WHERE result = '正常') AS normalInventory, " +
            "(SELECT COUNT(*) FROM inventory WHERE result = '异常') AS abnormalInventory, " +
            "0 AS assetGrowthRate, " +
            "0 AS repairDecreaseRate, " +
            "0 AS valueGrowthRate, " +
            "0 AS inventoryGrowthRate")
    DashboardStats getDashboardStats();

    @Select("SELECT status AS name, COUNT(*) AS value FROM assets GROUP BY status")
    List<StatusDistribution> getStatusDistribution();

    @Select("SELECT " +
            "DATE_FORMAT(date, '%Y-%m-%d') AS date, " +
            "COUNT(*) AS repairCount, " +
            "SUM(CASE WHEN status = '已完成' THEN 1 ELSE 0 END) AS completedCount " +
            "FROM assets_repair " +
            "WHERE date >= DATE_SUB(NOW(), INTERVAL 30 DAY) " +
            "GROUP BY DATE_FORMAT(date, '%Y-%m-%d') " +
            "ORDER BY date")
    List<Object> getRepairStats();

    @Select("SELECT " +
            "DATE_FORMAT(inventory_time, '%Y-%m') AS month, " +
            "COUNT(*) AS count " +
            "FROM inventory " +
            "WHERE inventory_time >= DATE_SUB(NOW(), INTERVAL 6 MONTH) " +
            "GROUP BY DATE_FORMAT(inventory_time, '%Y-%m') " +
            "ORDER BY month")
    InventoryTrend getInventoryTrend();
}

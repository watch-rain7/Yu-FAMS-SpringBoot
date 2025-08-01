package com.example.dto;

import java.math.BigDecimal;

public class DashboardStats {
    private Integer totalAssets;
    private Integer underRepair;
    private BigDecimal totalValue;
    private Integer inventoryCount;
    private Integer normalInventory;
    private Integer abnormalInventory;
    private BigDecimal assetGrowthRate;
    private BigDecimal repairDecreaseRate;
    private BigDecimal valueGrowthRate;
    private BigDecimal inventoryGrowthRate;

    public Integer getTotalAssets() {
        return totalAssets;
    }

    public void setTotalAssets(Integer totalAssets) {
        this.totalAssets = totalAssets;
    }

    public Integer getUnderRepair() {
        return underRepair;
    }

    public void setUnderRepair(Integer underRepair) {
        this.underRepair = underRepair;
    }

    public BigDecimal getTotalValue() {
        return totalValue;
    }

    public void setTotalValue(BigDecimal totalValue) {
        this.totalValue = totalValue;
    }

    public Integer getInventoryCount() {
        return inventoryCount;
    }

    public void setInventoryCount(Integer inventoryCount) {
        this.inventoryCount = inventoryCount;
    }

    public Integer getNormalInventory() {
        return normalInventory;
    }

    public void setNormalInventory(Integer normalInventory) {
        this.normalInventory = normalInventory;
    }

    public Integer getAbnormalInventory() {
        return abnormalInventory;
    }

    public void setAbnormalInventory(Integer abnormalInventory) {
        this.abnormalInventory = abnormalInventory;
    }

    public BigDecimal getAssetGrowthRate() {
        return assetGrowthRate;
    }

    public void setAssetGrowthRate(BigDecimal assetGrowthRate) {
        this.assetGrowthRate = assetGrowthRate;
    }

    public BigDecimal getRepairDecreaseRate() {
        return repairDecreaseRate;
    }

    public void setRepairDecreaseRate(BigDecimal repairDecreaseRate) {
        this.repairDecreaseRate = repairDecreaseRate;
    }

    public BigDecimal getValueGrowthRate() {
        return valueGrowthRate;
    }

    public void setValueGrowthRate(BigDecimal valueGrowthRate) {
        this.valueGrowthRate = valueGrowthRate;
    }

    public BigDecimal getInventoryGrowthRate() {
        return inventoryGrowthRate;
    }

    public void setInventoryGrowthRate(BigDecimal inventoryGrowthRate) {
        this.inventoryGrowthRate = inventoryGrowthRate;
    }
}

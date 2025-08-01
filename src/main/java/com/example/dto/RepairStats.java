package com.example.dto;

import lombok.Data;
import java.util.List;

@Data
public class RepairStats {
    private List<String> labels;
    private List<Integer> repairCounts;
    private List<Integer> completedCounts;

    public List<String> getLabels() {
        return labels;
    }

    public void setLabels(List<String> labels) {
        this.labels = labels;
    }

    public List<Integer> getCompletedCounts() {
        return completedCounts;
    }

    public void setCompletedCounts(List<Integer> completedCounts) {
        this.completedCounts = completedCounts;
    }

    public List<Integer> getRepairCounts() {
        return repairCounts;
    }

    public void setRepairCounts(List<Integer> repairCounts) {
        this.repairCounts = repairCounts;
    }
}
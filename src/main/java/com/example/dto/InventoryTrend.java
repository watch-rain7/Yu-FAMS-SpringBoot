package com.example.dto;

import lombok.Data;
import java.util.List;

@Data
public class InventoryTrend {
    private List<String> labels;
    private List<Integer> values;

    public List<String> getLabels() {
        return labels;
    }

    public void setLabels(List<String> labels) {
        this.labels = labels;
    }

    public List<Integer> getValues() {
        return values;
    }

    public void setValues(List<Integer> values) {
        this.values = values;
    }
}
package com.aa.coolreads.Book.dto;

import java.util.List;

public class StatisticsPieChartDTO {

    private List<SliceDTO> slices;

    public StatisticsPieChartDTO(List<SliceDTO> slices) {
        this.slices = slices;
    }

    public List<SliceDTO> getSlices() {
        return slices;
    }

    public void setSlices(List<SliceDTO> slices) {
        this.slices = slices;
    }
}

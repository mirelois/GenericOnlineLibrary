package com.aa.coolreads.Book.dto;

import com.aa.coolreads.Book.models.Slice;
import com.aa.coolreads.User.models.DefaultBookshelf;
import com.aa.coolreads.User.models.Gender;

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

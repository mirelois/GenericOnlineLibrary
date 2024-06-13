package com.aa.coolreads.Book.mappers;

import com.aa.coolreads.Book.dto.SliceDTO;
import com.aa.coolreads.Book.dto.StatisticsPieChartDTO;
import com.aa.coolreads.Book.models.Slice;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class StatisticsMapper {

    public SliceDTO toSliceDTO(Slice slice) {
        return new SliceDTO(slice.getCategoryName(), slice.getAmount());
    }

    public StatisticsPieChartDTO toStatisticsPieChartDTO(List<? extends Slice> slices) {
        return new StatisticsPieChartDTO(slices.stream().map(this::toSliceDTO).collect(Collectors.toList()));
    }


}

package com.aa.coolreads.Book.mappers;

import com.aa.coolreads.Book.dto.SliceInterfaceDTO;
import com.aa.coolreads.Book.dto.StatisticsChartDTO;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class StatisticsMapper {

    public StatisticsChartDTO toStatisticsPieChartDTO(List<SliceInterfaceDTO> slices) {

        List<String> labels = new ArrayList<>();
        List<Integer> data = new ArrayList<>();

        for (SliceInterfaceDTO slice : slices){
            labels.add(slice.getClassName());
            data.add(slice.getAmount());
        }

        return new StatisticsChartDTO(labels, data);

    }

    public StatisticsChartDTO toStatisticsPieChartDTO(Page<SliceInterfaceDTO> slices) {

        List<String> labels = new ArrayList<>();
        List<Integer> data = new ArrayList<>();

        for (SliceInterfaceDTO slice : slices){
            labels.add(slice.getClassName());
            data.add(slice.getAmount());
        }

        return new StatisticsChartDTO(labels, data);

    }

}

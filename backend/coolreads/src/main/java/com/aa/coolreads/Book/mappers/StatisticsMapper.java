package com.aa.coolreads.Book.mappers;

import com.aa.coolreads.Book.dto.SliceDTO;
import com.aa.coolreads.Book.dto.SliceInterfaceDTO;
import com.aa.coolreads.Book.dto.StatisticsPieChartDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Slice;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Component
public class StatisticsMapper {

    public StatisticsPieChartDTO toStatisticsPieChartDTO(List<SliceInterfaceDTO> slices) {

        List<SliceDTO> sliceDTOList = new ArrayList<>();

        for (SliceInterfaceDTO slice : slices){
            sliceDTOList.add(new SliceDTO(slice.getClassName(), slice.getAmount()));
        }

        return new StatisticsPieChartDTO(sliceDTOList);

    }

    public StatisticsPieChartDTO toStatisticsPieChartDTO(Page<SliceInterfaceDTO> slices) {

        List<SliceDTO> sliceDTOList = new ArrayList<>();

        for (SliceInterfaceDTO slice : slices) {
            sliceDTOList.add(new SliceDTO(slice.getClassName(), slice.getAmount()));
        }

        return new StatisticsPieChartDTO(sliceDTOList);

    }

}

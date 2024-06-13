package com.aa.coolreads.Book.mappers;

import com.aa.coolreads.Book.dto.SliceDTO;
import com.aa.coolreads.Book.dto.StatisticsPieChartDTO;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Component
public class StatisticsMapper {

    public StatisticsPieChartDTO toStatisticsPieChartDTO(Map<String, Integer> amountMap) {

        List<SliceDTO> sliceDTOList = new ArrayList<>();

        for (Map.Entry<String, Integer> entry : amountMap.entrySet()) {
            sliceDTOList.add(new SliceDTO(entry.getKey(), entry.getValue()));
        }

        return new StatisticsPieChartDTO(sliceDTOList);
    }

}

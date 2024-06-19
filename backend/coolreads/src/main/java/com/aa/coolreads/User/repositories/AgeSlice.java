package com.aa.coolreads.User.repositories;

import com.aa.coolreads.Book.dto.SliceInterfaceDTO;
import com.aa.coolreads.Book.services.StatisticService;
import com.aa.coolreads.User.models.DefaultBookshelf;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class AgeSlice implements Sliceable{


    public final StatisticService statisticService;

    public AgeSlice(StatisticService statisticService) {
        this.statisticService = statisticService;
    }

    public List<SliceInterfaceDTO> getSlices(DefaultBookshelf bookshelf, String isbn){
        return statisticService.getPersonalBooksRepository().getAgeSlicesByBookshelfName(bookshelf.name(), isbn);
    }
}

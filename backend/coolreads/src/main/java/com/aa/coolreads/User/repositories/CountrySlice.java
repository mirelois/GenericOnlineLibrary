package com.aa.coolreads.User.repositories;

import com.aa.coolreads.Book.dto.SliceInterfaceDTO;
import com.aa.coolreads.Book.services.StatisticService;
import com.aa.coolreads.User.models.DefaultBookshelf;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class CountrySlice implements Sliceable{

    private final StatisticService statisticService;

    public CountrySlice(StatisticService statisticService) {
        this.statisticService = statisticService;
    }

    @Override
    public List<SliceInterfaceDTO> getSlices(DefaultBookshelf bookshelf, String isbn){
        return statisticService.getPersonalBooksRepository().getCountrySlicesByBookshelfName(bookshelf.name(), isbn);
    }
}

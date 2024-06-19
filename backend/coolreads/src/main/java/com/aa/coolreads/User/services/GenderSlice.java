package com.aa.coolreads.User.services;

import com.aa.coolreads.Book.dto.SliceInterfaceDTO;
import com.aa.coolreads.Book.services.StatisticService;
import com.aa.coolreads.User.models.DefaultBookshelf;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class GenderSlice implements Sliceable {

    public final StatisticService statisticService;

    public GenderSlice(StatisticService statisticService) {
        this.statisticService = statisticService;
    }

    @Override
    public List<SliceInterfaceDTO> getSlices(DefaultBookshelf bookshelf, String isbn) {
        return statisticService.getPersonalBooksRepository().getGenderSlicesByBookshelfName(bookshelf.name(), isbn);
    }
}

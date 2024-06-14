package com.aa.coolreads.Book.services;

import com.aa.coolreads.Book.dto.SliceDTO;
import com.aa.coolreads.Book.dto.StatisticsNumberDTO;
import com.aa.coolreads.Book.dto.StatisticsPieChartDTO;
import com.aa.coolreads.Book.mappers.StatisticsMapper;
import com.aa.coolreads.User.models.DefaultBookshelf;
import com.aa.coolreads.User.repositories.PersonalBooksRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class StatisticService {

    private final PersonalBooksRepository personalBooksRepository;

    private final StatisticsMapper statisticsMapper;

    public StatisticService(PersonalBooksRepository personalBooksRepository, StatisticsMapper statisticsMapper) {
        this.personalBooksRepository = personalBooksRepository;
        this.statisticsMapper = statisticsMapper;
    }


    @Transactional
    public StatisticsNumberDTO getStatisticsNumbers(String isbn) {

        Integer currentlyReading = this.personalBooksRepository.getBooksSizeByBookShelfName(DefaultBookshelf.currently_reading.name(), isbn);
        Integer alreadyRead = this.personalBooksRepository.getBooksSizeByBookShelfName(DefaultBookshelf.already_read.name(), isbn);
        Integer didNotFinish = this.personalBooksRepository.getBooksSizeByBookShelfName(DefaultBookshelf.did_not_finish.name(), isbn);
        Integer wantToRead = this.personalBooksRepository.getBooksSizeByBookShelfName(DefaultBookshelf.want_to_read.name(), isbn);

        return new StatisticsNumberDTO(currentlyReading, alreadyRead, didNotFinish, wantToRead);
    }

    @Transactional
    public StatisticsPieChartDTO getStatisticsCountryPieChart(DefaultBookshelf bookshelf, String isbn) {

        List<SliceDTO> slices = this.personalBooksRepository.getCountrySlicesByBookshelfName(bookshelf.name(), isbn);

        return new StatisticsPieChartDTO(slices);
    }

    @Transactional
    public StatisticsPieChartDTO getStatisticsAgePieChart(DefaultBookshelf bookshelf, String isbn) {

        List<SliceDTO> slices = this.personalBooksRepository.getAgesByBookshelfName(bookshelf.name(), isbn);

        return new StatisticsPieChartDTO(slices);
    }

    @Transactional
    public StatisticsPieChartDTO getStatisticsGenderPieChart(DefaultBookshelf bookshelf, String isbn) {

        List<SliceDTO> slices = this.personalBooksRepository.getGenderSlicesByBookshelfName(bookshelf.name(), isbn);

        return new StatisticsPieChartDTO(slices);
    }

}

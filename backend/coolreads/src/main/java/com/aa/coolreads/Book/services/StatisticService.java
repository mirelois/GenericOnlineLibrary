package com.aa.coolreads.Book.services;

import com.aa.coolreads.Book.dto.StatisticsNumberDTO;
import com.aa.coolreads.Book.dto.StatisticsPieChartDTO;
import com.aa.coolreads.Book.mappers.StatisticsMapper;
import com.aa.coolreads.Book.models.CountrySlice;
import com.aa.coolreads.Book.models.Slice;
import com.aa.coolreads.Book.repositories.BookRepository;
import com.aa.coolreads.User.models.DefaultBookshelf;
import com.aa.coolreads.User.repositories.CustomerRepository;
import com.aa.coolreads.User.repositories.PersonalBooksRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;

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
    public StatisticsPieChartDTO getStatisticsCountryPieChart(String category, DefaultBookshelf bookshelf, String isbn) {

        List<CountrySlice> slices = this.personalBooksRepository.getCountrySlicesByBookshelfName(bookshelf.name(), isbn);

        return statisticsMapper.toStatisticsPieChartDTO(slices);
    }

}

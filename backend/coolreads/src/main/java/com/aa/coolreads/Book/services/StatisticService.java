package com.aa.coolreads.Book.services;

import com.aa.coolreads.Book.dto.SliceInterfaceDTO;
import com.aa.coolreads.Book.dto.StatisticsNumberDTO;
import com.aa.coolreads.Book.dto.StatisticsChartDTO;
import com.aa.coolreads.Book.mappers.StatisticsMapper;
import com.aa.coolreads.Book.models.TimeFrame;
import com.aa.coolreads.User.models.DefaultBookshelf;
import com.aa.coolreads.User.repositories.PersonalBooksRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StatisticService {

    private final PersonalBooksRepository personalBooksRepository;

    private final StatisticsMapper statisticsMapper;

    @Autowired
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
    public StatisticsChartDTO getStatisticsCountryPieChart(DefaultBookshelf bookshelf, String isbn) {

        List<SliceInterfaceDTO> slices = this.personalBooksRepository.getCountrySlicesByBookshelfName(bookshelf.name(), isbn);

        return statisticsMapper.toStatisticsPieChartDTO(slices);
    }

    @Transactional
    public StatisticsChartDTO getStatisticsAgePieChart(DefaultBookshelf bookshelf, String isbn) {

        List<SliceInterfaceDTO> slices = this.personalBooksRepository.getAgesByBookshelfName(bookshelf.name(), isbn);

        return statisticsMapper.toStatisticsPieChartDTO(slices);
    }

    @Transactional
    public StatisticsChartDTO getStatisticsGenderPieChart(DefaultBookshelf bookshelf, String isbn) {

        List<SliceInterfaceDTO> slices = this.personalBooksRepository.getGenderSlicesByBookshelfName(bookshelf.name(), isbn);

        return statisticsMapper.toStatisticsPieChartDTO(slices);
    }

    @Transactional
    public StatisticsChartDTO getTimeLineChart(DefaultBookshelf bookshelf, String isbn, TimeFrame timeFrame, Integer pageNumber, Integer pageSize){

        PageRequest pageable = PageRequest.of(pageNumber, pageSize);

        Page<SliceInterfaceDTO> slices = this.personalBooksRepository.getTimeLine(bookshelf.name(), isbn, timeFrame.name(), pageable);

        return statisticsMapper.toStatisticsPieChartDTO(slices);

    }

}

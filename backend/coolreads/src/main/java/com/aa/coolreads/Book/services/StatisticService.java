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

    private String processAge(Integer age) {
        if (age >= 0 && age < 13) {
            return "child";
        }else if (age >= 13 && age < 18) {
            return "teen";
        }else if (age >= 18 && age < 30) {
            return "young_adult";
        }else if (age >= 30 && age < 60) {
            return "adult";
        }else if (age >= 60) {
            return "elder";
        }else{
            throw new IllegalArgumentException("Invalid age");
        }
    }

    private StatisticsPieChartDTO processAges(List<Integer> ages) {

        Map<String, Integer> amountMap = new HashMap<>();
        String ageClass;

        for (Integer age : ages) {
            ageClass = processAge(age);
            if (amountMap.containsKey(ageClass)) {
                amountMap.put(ageClass, amountMap.get(ageClass) + 1);
            }else{
                amountMap.put(ageClass, 1);
            }
        }

        return statisticsMapper.toStatisticsPieChartDTO(amountMap);

    }

    @Transactional
    public StatisticsPieChartDTO getStatisticsAgePieChart(DefaultBookshelf bookshelf, String isbn) {

        List<Integer> ages = this.personalBooksRepository.getAgesByBookshelfName(bookshelf.name(), isbn);

        return processAges(ages);
    }

    @Transactional
    public StatisticsPieChartDTO getStatisticsGenderPieChart(DefaultBookshelf bookshelf, String isbn) {

        List<SliceDTO> slices = this.personalBooksRepository.getGenderSlicesByBookshelfName(bookshelf.name(), isbn);

        return new StatisticsPieChartDTO(slices);
    }

}

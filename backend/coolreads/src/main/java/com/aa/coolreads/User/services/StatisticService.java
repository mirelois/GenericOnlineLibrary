package com.aa.coolreads.User.services;

import com.aa.coolreads.Book.dto.StatisticsNumberDTO;
import com.aa.coolreads.Book.dto.StatisticsPieChartDTO;
import com.aa.coolreads.Book.repositories.BookRepository;
import com.aa.coolreads.User.models.DefaultBookshelf;
import com.aa.coolreads.User.repositories.CustomerRepository;
import com.aa.coolreads.User.repositories.PersonalBooksRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Slice;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StatisticService {

    private final BookRepository bookRepository;

    private final CustomerRepository customerRepository;

    private final PersonalBooksRepository personalBooksRepository;

    @Autowired
    public StatisticService(BookRepository bookRepository, CustomerRepository customerRepository, PersonalBooksRepository personalBooksRepository) {
        this.bookRepository = bookRepository;
        this.customerRepository = customerRepository;
        this.personalBooksRepository = personalBooksRepository;
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
    public StatisticsPieChartDTO getStatisticsPieChart(String category, DefaultBookshelf defaultBookshelf, String isbn) {

        List<Slice> slices = this.personalBooksRepository.get

    }
}

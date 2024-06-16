package com.aa.coolreads.Book.controllers;

import com.aa.coolreads.Book.dto.StatisticsChartDTO;
import com.aa.coolreads.Book.dto.StatisticsNumberDTO;
import com.aa.coolreads.Book.models.TimeFrame;
import com.aa.coolreads.Book.services.StatisticService;
import com.aa.coolreads.User.models.DefaultBookshelf;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/book/{isbn}/stats")
public class StatisticsController {

    private final StatisticService statisticService;

    @Autowired

    public StatisticsController(StatisticService statisticService) {
        this.statisticService = statisticService;
    }

    @GetMapping("/pie/gender")
    public ResponseEntity<StatisticsChartDTO> getStatisticsGenderPie(@PathVariable String isbn, @RequestParam String defaultBookshelf) {

        return ResponseEntity.ok().body(this.statisticService.getStatisticsGenderPieChart(DefaultBookshelf.valueOf(defaultBookshelf.toLowerCase()), isbn));
    }

    @GetMapping("/pie/country")
    public ResponseEntity<StatisticsChartDTO> getStatisticsCountryPie(@PathVariable String isbn, @RequestParam String defaultBookshelf) {
        return ResponseEntity.ok().body(this.statisticService.getStatisticsCountryPieChart(DefaultBookshelf.valueOf(defaultBookshelf.toLowerCase()), isbn));
    }

    @GetMapping("/pie/age")
    public ResponseEntity<StatisticsChartDTO> getStatisticsAgePie(@PathVariable String isbn, @RequestParam String defaultBookshelf) {
        return ResponseEntity.ok().body(this.statisticService.getStatisticsAgePieChart(DefaultBookshelf.valueOf(defaultBookshelf.toLowerCase()), isbn));
    }

    @GetMapping("/line")
    public ResponseEntity<StatisticsChartDTO> getStatisticsLine(@PathVariable String isbn, @RequestParam String defaultBookshelf, @RequestParam String timeFrame, @RequestParam Integer pageNumber, @RequestParam Integer pageSize) {
        return ResponseEntity.ok().body(this.statisticService.getTimeLineChart(
                DefaultBookshelf.valueOf(defaultBookshelf.toLowerCase()),
                isbn,
                TimeFrame.valueOf(timeFrame.toLowerCase()),
                pageNumber,
                pageSize ));
    }

    @GetMapping("/numbers")
    public ResponseEntity<StatisticsNumberDTO> getStatisticsNumber(@PathVariable String isbn){
        return ResponseEntity.ok().body(this.statisticService.getStatisticsNumbers(isbn));
    }
}

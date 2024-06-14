package com.aa.coolreads.Book.dto;

public class StatisticsNumberDTO {

    private Integer currentlyReading;

    private Integer alreadyRead;

    private Integer didNotFinish;

    private Integer wantToRead;


    public StatisticsNumberDTO(Integer currentlyReading, Integer alreadyRead, Integer didNotFinish, Integer wantToRead) {

        this.currentlyReading = currentlyReading;
        this.alreadyRead = alreadyRead;
        this.didNotFinish = didNotFinish;
        this.wantToRead = wantToRead;
    }

}

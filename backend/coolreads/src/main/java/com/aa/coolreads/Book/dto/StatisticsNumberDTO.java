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

    public Integer getCurrentlyReading() {
        return currentlyReading;
    }

    public void setCurrentlyReading(Integer currentlyReading) {
        this.currentlyReading = currentlyReading;
    }

    public Integer getAlreadyRead() {
        return alreadyRead;
    }

    public void setAlreadyRead(Integer alreadyRead) {
        this.alreadyRead = alreadyRead;
    }

    public Integer getDidNotFinish() {
        return didNotFinish;
    }

    public void setDidNotFinish(Integer didNotFinish) {
        this.didNotFinish = didNotFinish;
    }

    public Integer getWantToRead() {
        return wantToRead;
    }

    public void setWantToRead(Integer wantToRead) {
        this.wantToRead = wantToRead;
    }
}

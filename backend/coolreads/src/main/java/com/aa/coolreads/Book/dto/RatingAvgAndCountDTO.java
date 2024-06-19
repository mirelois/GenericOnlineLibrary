package com.aa.coolreads.Book.dto;

public class RatingAvgAndCountDTO {

    private double average;

    private Long count;

    public RatingAvgAndCountDTO(){}

    public RatingAvgAndCountDTO(double average, Long count) {
        this.average = average;
        this.count = count;
    }

    public double getAverage() {
        return average;
    }

    public void setAverage(double average) {
        this.average = average;
    }

    public Long getCount() {
        return count;
    }

    public void setCount(Long count) {
        this.count = count;
    }
}

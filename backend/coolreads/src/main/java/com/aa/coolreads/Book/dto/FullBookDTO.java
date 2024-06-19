package com.aa.coolreads.Book.dto;

import java.util.Date;
import java.util.Set;

public class FullBookDTO extends BookDTO{
    private double ratingAverage;

    private Long ratingCount;

    private Long reviewCount;

    public FullBookDTO(){}

    public FullBookDTO(String isbn, String title, String description, Date launchDate, int totalPageNumbers, String publisherName, Set<String> genres, String authorUsername, String imageUrl, double ratingAverage, Long ratingCount, Long reviewCount) {
        super(isbn, title, description, launchDate, totalPageNumbers, publisherName, genres, authorUsername, imageUrl);
        this.ratingAverage = ratingAverage;
        this.ratingCount = ratingCount;
        this.reviewCount = reviewCount;
    }

    public double getRatingAverage() {
        return ratingAverage;
    }

    public void setRatingAverage(double ratingAverage) {
        this.ratingAverage = ratingAverage;
    }

    public Long getRatingCount() {
        return ratingCount;
    }

    public void setRatingCount(Long ratingCount) {
        this.ratingCount = ratingCount;
    }

    public Long getReviewCount() {
        return reviewCount;
    }

    public void setReviewCount(Long reviewCount) {
        this.reviewCount = reviewCount;
    }
}

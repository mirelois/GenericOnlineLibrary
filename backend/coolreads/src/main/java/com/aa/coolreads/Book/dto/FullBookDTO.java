package com.aa.coolreads.Book.dto;

import java.util.Date;
import java.util.Set;

public class FullBookDTO extends BookDTO{
    private double ratingAverage;

    private Set<BookReviewDTO> reviews;

    public FullBookDTO(){}

    public FullBookDTO(String isbn, String title, String description, Date launchDate, int totalPageNumbers, String publisherName, Set<String> genres, String imageUrl, String authorUsername, double ratingAverage, Set<BookReviewDTO> reviews) {
        super(isbn, title, description, launchDate, totalPageNumbers, publisherName, genres, authorUsername, imageUrl);
        this.ratingAverage = ratingAverage;
        this.reviews = reviews;
    }

    public Set<BookReviewDTO> getReviews() {
        return reviews;
    }

    public void setReviews(Set<BookReviewDTO> reviews) {
        this.reviews = reviews;
    }

    public double getRatingAverage() {
        return ratingAverage;
    }

    public void setRatingAverage(double ratingAverage) {
        this.ratingAverage = ratingAverage;
    }
}

package com.aa.coolreads.Book.dto;

import java.util.Date;
import java.util.Set;

public class FullBookDTO extends BookDTO{
    private double ratingAverage;

    private Set<BookRatingDTO> ratings;

    private Set<BookReviewDTO> reviews;

    public FullBookDTO(){}

    public FullBookDTO(String isbn, String title, String description, Date launchDate, int totalPageNumbers, String publisherName, Set<String> genres, String imageUrl, String authorUsername, double ratingAverage, Set<BookRatingDTO> ratings, Set<BookReviewDTO> reviews) {
        super(isbn, title, description, launchDate, totalPageNumbers, publisherName, genres, authorUsername, imageUrl);
        this.ratingAverage = ratingAverage;
        this.ratings = ratings;
        this.reviews = reviews;
    }

    public Set<BookRatingDTO> getRatings() {
        return ratings;
    }

    public void setRatings(Set<BookRatingDTO> ratings) {
        this.ratings = ratings;
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

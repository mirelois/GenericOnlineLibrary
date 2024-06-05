package com.aa.coolreads.Book.dto;

import java.util.Date;

public class SimpleReviewDTO {
    private Double rating;

    private String title;

    private String description;

    private Date postDate;

    public SimpleReviewDTO(){}

    public SimpleReviewDTO(Double rating, String title, String description, Date postDate) {
        this.rating = rating;
        this.title = title;
        this.description = description;
        this.postDate = postDate;
    }

    public Double getRating() {
        return rating;
    }

    public void setRating(Double rating) {
        this.rating = rating;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Date getPostDate() {
        return postDate;
    }

    public void setPostDate(Date postDate) {
        this.postDate = postDate;
    }
}

package com.aa.coolreads.Book.dto;

import com.aa.coolreads.Book.models.LikeType;

import java.util.Date;
import java.util.Map;
import java.util.Set;

public class BookReviewDTO extends SimpleReviewDTO{

    private String customerUrl;

    private String customerUsername;

    private Map<LikeType, Integer> likes;

    private Set<BookReviewCommentDTO> reviewComments;

    public BookReviewDTO() {}

    public BookReviewDTO(Double rating, String description, Date postDate, Set<BookReviewCommentDTO> reviewComments, Map<LikeType, Integer> likes, String customerUsername, String customerUrl) {
        super(rating, description, postDate);
        this.reviewComments = reviewComments;
        this.likes = likes;
        this.customerUsername = customerUsername;
        this.customerUrl = customerUrl;
    }

    public Map<LikeType, Integer> getLikes() {
        return likes;
    }

    public void setLikes(Map<LikeType, Integer> likes) {
        this.likes = likes;
    }

    public Set<BookReviewCommentDTO> getReviewComments() {
        return reviewComments;
    }

    public void setReviewComments(Set<BookReviewCommentDTO> reviewComments) {
        this.reviewComments = reviewComments;
    }

    public String getCustomerUrl() {
        return customerUrl;
    }

    public void setCustomerUrl(String customerUrl) {
        this.customerUrl = customerUrl;
    }

    public String getCustomerUsername() {
        return customerUsername;
    }

    public void setCustomerUsername(String customerUsername) {
        this.customerUsername = customerUsername;
    }
}

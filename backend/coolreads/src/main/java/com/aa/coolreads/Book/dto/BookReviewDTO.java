package com.aa.coolreads.Book.dto;

import com.aa.coolreads.Book.models.LikeType;

import java.util.Date;
import java.util.Map;
import java.util.Set;

public class BookReviewDTO extends SimpleReviewDTO{

    private String customerUrl;

    private String customerUsername;

    private Map<LikeType, Integer> likes;

    private Integer commentsSize;

    public BookReviewDTO() {}

    public BookReviewDTO(String description, Date postDate, Integer commentsSize, Map<LikeType, Integer> likes, String customerUsername, String customerUrl) {
        super(description, postDate);
        this.likes = likes;
        this.commentsSize = commentsSize;
        this.customerUsername = customerUsername;
        this.customerUrl = customerUrl;
    }

    public Map<LikeType, Integer> getLikes() {
        return likes;
    }

    public void setLikes(Map<LikeType, Integer> likes) {
        this.likes = likes;
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

    public Integer getCommentsSize() {
        return commentsSize;
    }

    public void setCommentsSize(Integer commentsSize) {
        this.commentsSize = commentsSize;
    }
}

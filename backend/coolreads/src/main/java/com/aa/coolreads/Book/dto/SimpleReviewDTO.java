package com.aa.coolreads.Book.dto;

import java.util.Date;

public class SimpleReviewDTO {

    private String description;

    private Date postDate;

    public SimpleReviewDTO(){}

    public SimpleReviewDTO(String description, Date postDate) {
        this.description = description;
        this.postDate = postDate;
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

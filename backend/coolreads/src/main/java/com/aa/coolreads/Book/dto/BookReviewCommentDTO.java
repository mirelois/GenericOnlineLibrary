package com.aa.coolreads.Book.dto;

public class BookReviewCommentDTO {
    private String comment;

    private String CustomerUserName;

    public BookReviewCommentDTO() {}

    public BookReviewCommentDTO(String comment, String customerUserName) {
        this.comment = comment;
        CustomerUserName = customerUserName;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public String getCustomerUserName() {
        return CustomerUserName;
    }

    public void setCustomerUserName(String customerUserName) {
        CustomerUserName = customerUserName;
    }
}

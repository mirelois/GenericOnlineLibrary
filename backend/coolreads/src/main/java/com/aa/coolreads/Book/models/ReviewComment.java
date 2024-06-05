package com.aa.coolreads.Book.models;

import com.aa.coolreads.User.models.Customer;
import jakarta.persistence.*;

@Entity
@IdClass(ReviewCommentId.class)
public class ReviewComment {

    @Column(columnDefinition = "TEXT")
    private String comment;

    @Id
    @ManyToOne
    private Customer customer;

    @Id
    @ManyToOne
    private Review review;

    public ReviewComment(){}

    public ReviewComment(String comment, Customer customer, Review review) {
        this.comment = comment;
        this.customer = customer;
        this.review = review;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public Review getReview() {
        return review;
    }

    public void setReview(Review review) {
        this.review = review;
    }
}

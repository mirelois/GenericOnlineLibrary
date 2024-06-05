package com.aa.coolreads.Book.models;

import com.aa.coolreads.User.models.Customer;
import jakarta.persistence.*;

@Entity
@IdClass(ReviewLikeId.class)
public class ReviewLike {
    @Enumerated(EnumType.STRING)
    private LikeType likeType;

    @Id
    @ManyToOne
    private Customer customer;

    @Id
    @ManyToOne
    private Review review;

    public ReviewLike(){}

    public ReviewLike(LikeType likeType, Customer customer, Review review) {
        this.likeType = likeType;
        this.customer = customer;
        this.review = review;
    }

    public LikeType getLikeType() {
        return likeType;
    }

    public void setLikeType(LikeType likeType) {
        this.likeType = likeType;
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

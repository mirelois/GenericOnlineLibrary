package com.aa.coolreads.Book.models;

import com.aa.coolreads.User.models.Customer;

import java.io.Serializable;
import java.util.Objects;

public class ReviewLikeId implements Serializable {
    private Customer customer;
    private Review review;

    public ReviewLikeId() {}

    public ReviewLikeId(Customer customer, Review review) {
        this.customer = customer;
        this.review = review;
    }

    @Override
    public int hashCode() {
        return Objects.hash(customer, review);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        ReviewLikeId that = (ReviewLikeId) obj;
        return Objects.equals(customer, that.customer) &&
                Objects.equals(review, that.review);
    }
}

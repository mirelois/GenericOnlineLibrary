package com.aa.coolreads.Book.models;

import com.aa.coolreads.User.models.Customer;

import java.util.Objects;

public class ReviewCommentId {
    private String customer;
    private ReviewId review;

    public ReviewCommentId() {}

    public ReviewCommentId(String customer, ReviewId review) {
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
        ReviewCommentId that = (ReviewCommentId) obj;
        return Objects.equals(customer, that.customer) &&
                Objects.equals(review, that.review);
    }
}

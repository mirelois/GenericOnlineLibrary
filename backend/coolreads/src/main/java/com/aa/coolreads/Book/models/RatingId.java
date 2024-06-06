package com.aa.coolreads.Book.models;

import java.io.Serializable;
import java.util.Objects;

public class RatingId implements Serializable{
    private String customer;
    private String book;

    public RatingId() {}

    public RatingId(String customer, String book) {
        this.customer = customer;
        this.book = book;
    }

    @Override
    public int hashCode() {
        return Objects.hash(customer, book);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        RatingId that = (RatingId) obj;
        return Objects.equals(customer, that.customer) &&
                Objects.equals(book, that.book);
    }
}
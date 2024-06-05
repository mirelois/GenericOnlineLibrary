package com.aa.coolreads.Book.models;

import com.aa.coolreads.User.models.Customer;

import java.io.Serializable;
import java.util.Objects;

public class ReviewId implements Serializable {
    private Customer customer;
    private Book book;

    public ReviewId() {}

    public ReviewId(Customer customer, Book book) {
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
        ReviewId that = (ReviewId) obj;
        return Objects.equals(customer, that.customer) &&
                Objects.equals(book, that.book);
    }
}

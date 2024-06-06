package com.aa.coolreads.Book.models;

import com.aa.coolreads.User.models.Customer;
import jakarta.persistence.*;

@Entity
@IdClass(RatingId.class)
public class Rating {
    private Double rating;

    @Id
    @ManyToOne
    @JoinColumn(name="customer_username", referencedColumnName="username")
    private Customer customer;

    @Id
    @ManyToOne
    @JoinColumn(name="book_isbn", referencedColumnName="isbn")
    private Book book;

    public Rating() {}

    public Rating(Double rating, Customer customer, Book book) {
        this.rating = rating;
        this.customer = customer;
        this.book = book;
    }

    public Double getRating() {
        return this.rating;
    }

    public void setRating(Double rating) {
        this.rating = rating;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public Book getBook() {
        return book;
    }

    public void setBook(Book book) {
        this.book = book;
    }
}

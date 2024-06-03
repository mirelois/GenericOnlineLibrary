package com.aa.coolreads.Book.models;

import com.aa.coolreads.User.models.Customer;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;

import java.io.Serializable;

@Entity
public class BookRating implements Serializable {

    private double rating;

    @Id
    @ManyToOne
    private Book book;

    @Id
    @ManyToOne
    private Customer customer;

    public BookRating() {}

    public BookRating(double rating, Book book, Customer customer) {
        this.book = book;
        this.customer = customer;
        this.rating = rating;
    }

    public double getRating() {
        return rating;
    }

    public void setRating(double rating) {
        this.rating = rating;
    }

    public Book getBook() {
        return book;
    }

    public void setBook(Book book) {
        this.book = book;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }
}

package com.aa.coolreads.Book.models;

import com.aa.coolreads.User.models.Customer;
import jakarta.persistence.*;

import java.io.Serializable;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Entity
@IdClass(ReviewId.class)
public class Review implements Serializable {

    @Column(columnDefinition = "TEXT")
    private String description;

    @Temporal(TemporalType.DATE)
    private Date postDate;

    private Double rating;

    @OneToMany(mappedBy = "review", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<ReviewLike> reviewLikes;

    @OneToMany(mappedBy = "review", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<ReviewComment> reviewComments;

    @Id
    @ManyToOne
    @JoinColumn(name="customer_username", referencedColumnName="username")
    private Customer customer;

    @Id
    @ManyToOne
    @JoinColumn(name="book_isbn", referencedColumnName="isbn")
    private Book book;

    public Review(){}

    public Review(Double rating, Customer customer, Book book) {
        this.rating = rating;
        this.reviewLikes = new HashSet<>();
        this.reviewComments = new HashSet<>();
        this.customer = customer;
        this.book = book;
    }

    public Review(String description, Date postDate, Customer customer, Book book) {
        this.description = description;
        this.postDate = postDate;
        this.reviewLikes = new HashSet<>();
        this.reviewComments = new HashSet<>();
        this.customer = customer;
        this.book = book;
    }

    public Review(String description, Date postDate, Double rating, Customer customer, Book book) {
        this.description = description;
        this.postDate = postDate;
        this.rating = rating;
        this.reviewLikes = new HashSet<>();
        this.reviewComments = new HashSet<>();
        this.customer = customer;
        this.book = book;
    }

    public Review(String description, Date postDate, Double rating, Customer customer, Book book, Set<ReviewLike> reviewLikes, Set<ReviewComment> reviewComments) {
        this.description = description;
        this.postDate = postDate;
        this.rating = rating;
        this.reviewLikes = reviewLikes;
        this.reviewComments = reviewComments;
        this.customer = customer;
        this.book = book;
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

    public Set<ReviewLike> getReviewLikes() {
        return reviewLikes;
    }

    public void setReviewLikes(Set<ReviewLike> reviewLikes) {
        this.reviewLikes = reviewLikes;
    }

    public Set<ReviewComment> getReviewComments() {
        return reviewComments;
    }

    public void setReviewComments(Set<ReviewComment> reviewComments) {
        this.reviewComments = reviewComments;
    }

    public Double getRating() {
        return rating;
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

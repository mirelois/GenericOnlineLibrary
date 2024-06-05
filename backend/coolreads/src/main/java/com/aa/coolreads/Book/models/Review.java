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

    private String title;

    @Column(columnDefinition = "TEXT")
    private String description;

    @Temporal(TemporalType.DATE)
    private Date postDate;

    private Double rating;

    @OneToMany
    private Set<ReviewLike> reviewLikes;

    @OneToMany
    private Set<ReviewComment> reviewComments;

    @Id
    @ManyToOne
    private Customer customer;

    @Id
    @ManyToOne
    private Book book;

    public Review(){}

    public Review(String title, String description, Date postDate, Double rating, Customer customer, Book book) {
        this.title = title;
        this.description = description;
        this.postDate = postDate;
        this.rating = rating;
        this.reviewLikes = new HashSet<>();
        this.reviewComments = new HashSet<>();
        this.customer = customer;
        this.book = book;
    }

    public Review(String title, String description, Date postDate, Double rating, Customer customer, Book book, Set<ReviewLike> reviewLikes, Set<ReviewComment> reviewComments) {
        this.title = title;
        this.description = description;
        this.postDate = postDate;
        this.rating = rating;
        this.reviewLikes = reviewLikes;
        this.reviewComments = reviewComments;
        this.customer = customer;
        this.book = book;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
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

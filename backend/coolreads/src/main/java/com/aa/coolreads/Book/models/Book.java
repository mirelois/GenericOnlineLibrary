package com.aa.coolreads.Book.models;

import com.aa.coolreads.Book.dto.BookDTO;
import com.aa.coolreads.User.models.Author;
import jakarta.persistence.*;

import java.io.Serializable;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Entity
public class Book implements Serializable {
    @Id
    private String isbn;

    private String title;

    private String description;

    private Date launchDate;

    private int totalPageNumbers;

    @ManyToOne
    private Publisher publisher;

    @ManyToMany
    private Set<Genre> genres;

    @OneToMany
    private Set<BookRating> ratings;

    @OneToMany
    private Set<Review> reviews;

    @ManyToOne
    private Author  author;

    public Book(){}

    public Book(String isbn, String title, String description, Date launchDate, int totalPageNumbers, Publisher publisher, Set<Genre> genres, Author author){
        this.isbn = isbn;
        this.title = title;
        this.description = description;
        this.launchDate = launchDate;
        this.totalPageNumbers = totalPageNumbers;
        this.publisher = publisher;
        this.genres = genres;
        this.ratings = new HashSet<>();
        this.reviews = new HashSet<>();
        this.author = author;
    }

    public Book(String isbn, String title, String description, Date launchDate, int totalPageNumbers, Publisher publisher, Set<Genre> genres, Set<BookRating> ratings, Set<Review> reviews, Author author) {
        this.isbn = isbn;
        this.title = title;
        this.description = description;
        this.launchDate = launchDate;
        this.totalPageNumbers = totalPageNumbers;
        this.publisher = publisher;
        this.genres = genres;
        this.ratings = ratings;
        this.reviews = reviews;
        this.author = author;
    }

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
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

    public Date getLaunchDate() {
        return launchDate;
    }

    public void setLaunchDate(Date launchDate) {
        this.launchDate = launchDate;
    }

    public int getTotalPageNumbers() {
        return totalPageNumbers;
    }

    public void setTotalPageNumbers(int totalPageNumbers) {
        this.totalPageNumbers = totalPageNumbers;
    }

    public Publisher getPublisher() {
        return publisher;
    }

    public void setPublisher(Publisher publisher) {
        this.publisher = publisher;
    }

    public Set<Genre> getGenres() {
        return genres;
    }

    public void setGenres(Set<Genre> genres) {
        this.genres = genres;
    }

    public Set<BookRating> getRatings() {
        return ratings;
    }

    public void setRatings(Set<BookRating> ratings) {
        this.ratings = ratings;
    }

    public Set<Review> getReviews() {
        return reviews;
    }

    public void setReviews(Set<Review> reviews) {
        this.reviews = reviews;
    }

    public Author getAuthor() {
        return author;
    }

    public void setAuthor(Author author) {
        this.author = author;
    }
}

package com.aa.coolreads.Book;

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
    private Set<Genre> genres = new HashSet<>();

    public Book(){}

    public Book(String isbn, String title, String description, Date launchDate, int totalPageNumbers, Publisher publisher, Set<Genre> genres) {
        this.isbn = isbn;
        this.title = title;
        this.description = description;
        this.launchDate = launchDate;
        this.totalPageNumbers = totalPageNumbers;
        this.publisher = publisher;
        this.genres = genres;
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
}

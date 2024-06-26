package com.aa.coolreads.Book.dto;

import com.aa.coolreads.Book.models.Book;

import java.util.Date;
import java.util.Set;

public class BookDTO {
    private String isbn;

    private String title;

    private String description;

    private Date launchDate;

    private int totalPageNumbers;

    private String publisherName;

    private String authorUsername;

    private Set<String> genres;

    private String imageUrl;

    public BookDTO(){}

    public BookDTO(String isbn, String title, String description, Date launchDate, int totalPageNumbers, String publisherName, Set<String> genres, String authorUsername, String imageUrl) {
        this.isbn = isbn;
        this.title = title;
        this.description = description;
        this.launchDate = launchDate;
        this.totalPageNumbers = totalPageNumbers;
        this.publisherName = publisherName;
        this.genres = genres;
        this.authorUsername = authorUsername;
        this.imageUrl = imageUrl;
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
        return this.description;
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

    public String getPublisherName() {
        return publisherName;
    }

    public void setPublisherName(String publisherName) {
        this.publisherName = publisherName;
    }

    public Set<String> getGenres() {
        return genres;
    }

    public void setGenres(Set<String> genres) {
        this.genres = genres;
    }

    public String getAuthorUsername() {
        return authorUsername;
    }

    public void setAuthorUsername(String authorUsername) {
        this.authorUsername = authorUsername;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }
}

package com.aa.coolreads.Book.models;

import jakarta.persistence.*;

import java.io.Serializable;
import java.util.Date;
import java.util.Set;

@Entity
public class Review implements Serializable {
    @Id
    @GeneratedValue
    private Long id;

    private String title;

    @Column(columnDefinition = "TEXT")
    private String description;

    private Date postDate;

    @OneToMany
    private Set<ReviewLike> reviewLikes;

    @OneToMany
    private Set<ReviewComment> reviewComments;

    public Review(){}

    public Review(String title, String description, Date postDate, Set<ReviewLike> reviewLikes, Set<ReviewComment> reviewComments) {
        this.title = title;
        this.description = description;
        this.postDate = postDate;
        this.reviewLikes = reviewLikes;
        this.reviewComments = reviewComments;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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
}

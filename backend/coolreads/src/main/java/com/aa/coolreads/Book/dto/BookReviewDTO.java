package com.aa.coolreads.Book.dto;

import com.aa.coolreads.Book.models.LikeType;

import java.util.Date;
import java.util.Map;
import java.util.Set;

public class BookReviewDTO {
    private String title;

    private String description;

    private Date postDate;

    private Map<LikeType, Integer> likes;

    private Set<BookReviewCommentDTO> reviewComments;

    public BookReviewDTO() {}

    public BookReviewDTO(String title, String description, Date postDate, Map<LikeType, Integer> likes, Set<BookReviewCommentDTO> reviewComments) {
        this.title = title;
        this.description = description;
        this.postDate = postDate;
        this.likes = likes;
        this.reviewComments = reviewComments;
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

    public Map<LikeType, Integer> getLikes() {
        return likes;
    }

    public void setLikes(Map<LikeType, Integer> likes) {
        this.likes = likes;
    }

    public Set<BookReviewCommentDTO> getReviewComments() {
        return reviewComments;
    }

    public void setReviewComments(Set<BookReviewCommentDTO> reviewComments) {
        this.reviewComments = reviewComments;
    }
}

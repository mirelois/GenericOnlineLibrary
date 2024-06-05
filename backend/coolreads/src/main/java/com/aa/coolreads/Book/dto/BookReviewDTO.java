package com.aa.coolreads.Book.dto;

import com.aa.coolreads.Book.models.LikeType;

import java.util.Date;
import java.util.Map;
import java.util.Set;

public class BookReviewDTO extends SimpleReviewDTO{

    private Map<LikeType, Integer> likes;

    private Set<BookReviewCommentDTO> reviewComments;

    public BookReviewDTO() {}

    public BookReviewDTO(Double rating, String description, Date postDate, Map<LikeType, Integer> likes, Set<BookReviewCommentDTO> reviewComments) {
        super(rating, description, postDate);
        this.likes = likes;
        this.reviewComments = reviewComments;
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

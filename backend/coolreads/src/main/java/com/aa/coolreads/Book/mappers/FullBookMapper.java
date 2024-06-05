package com.aa.coolreads.Book.mappers;

import com.aa.coolreads.Book.dto.*;
import com.aa.coolreads.Book.models.*;
import com.aa.coolreads.User.models.Author;
import com.aa.coolreads.User.models.Customer;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

@Component
public class FullBookMapper {
    public Book toBook(FullBookDTO bookDTO, Publisher publisher, Set<Genre> genres, Set<Review> reviews, Author author) {
        return new Book(bookDTO.getIsbn(), bookDTO.getTitle(), bookDTO.getDescription(),
                bookDTO.getLaunchDate(), bookDTO.getTotalPageNumbers(),
                publisher, genres, reviews, author, bookDTO.getImageUrl());
    }

    public Book toBook(BookDTO bookDTO, Publisher publisher, Set<Genre> genres, Author author) {
        return new Book(bookDTO.getIsbn(), bookDTO.getTitle(), bookDTO.getDescription(),
                bookDTO.getLaunchDate(), bookDTO.getTotalPageNumbers(),
                publisher, genres, author, bookDTO.getImageUrl());
    }

    public Map<LikeType, Integer> toLikesDTO(Set<ReviewLike> reviewLikes){
        Map<LikeType, Integer> likes = new HashMap<>();

        for(LikeType likeType: LikeType.values()){
            likes.put(likeType, 0);
        }

        for(ReviewLike reviewLike: reviewLikes){
            likes.put(reviewLike.getLikeType(), likes.get(reviewLike.getLikeType()) + 1);
        }

        return likes;
    }

    public Set<BookReviewCommentDTO> toSetBookReviewCommentDTO(Set<ReviewComment> reviewComment){
        return reviewComment.stream().map(comment -> new BookReviewCommentDTO(comment.getComment(), comment.getCustomer().getUsername())).collect(Collectors.toSet());
    }

    public BookReviewDTO toBookReviewDTO(Review review, Integer commentsSize){
        return new BookReviewDTO(review.getRating(), review.getDescription(), review.getPostDate(), commentsSize, toLikesDTO(review.getReviewLikes()), review.getCustomer().getUsername(), review.getCustomer().getProfileImageUrl());
    }

    public FullBookDTO toFullBookDTO(Book book, double averageRating) {
        Set<String> genres = book.getGenres().stream().map(Genre::getGenreType).collect(Collectors.toSet());

        return new FullBookDTO(book.getIsbn(), book.getTitle(), book.getDescription(), book.getLaunchDate(),
                book.getTotalPageNumbers(), book.getPublisher().getName(), genres, book.getImageUrl(), book.getAuthor().getUsername(), averageRating);
    }

    public BookDTO toBookDTO(Book book) {
        Set<String> genres = book.getGenres().stream().map(Genre::getGenreType).collect(Collectors.toSet());

        return new BookDTO(book.getIsbn(), book.getTitle(), book.getDescription(), book.getLaunchDate(),
                book.getTotalPageNumbers(), book.getPublisher().getName(), genres, book.getAuthor().getUsername(), book.getImageUrl());
    }

    public Review toReview(SimpleReviewDTO simpleReviewDTO, Customer customer, Book book){
        return new Review(simpleReviewDTO.getDescription(), simpleReviewDTO.getPostDate(), simpleReviewDTO.getRating(), customer, book);
    }

}

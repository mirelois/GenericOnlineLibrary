package com.aa.coolreads.Book.mappers;

import com.aa.coolreads.Book.dto.BookRatingDTO;
import com.aa.coolreads.Book.dto.BookReviewCommentDTO;
import com.aa.coolreads.Book.dto.BookReviewDTO;
import com.aa.coolreads.Book.dto.FullBookDTO;
import com.aa.coolreads.Book.models.*;
import com.aa.coolreads.User.models.Author;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

@Component
public class FullBookMapper {
    public Book toBook(FullBookDTO bookDTO, Publisher publisher, Set<Genre> genres, Set<BookRating> ratings, Set<Review> reviews, Author author) {
        return new Book(bookDTO.getIsbn(), bookDTO.getTitle(), bookDTO.getDescription(),
                bookDTO.getLaunchDate(), bookDTO.getTotalPageNumbers(),
                publisher, genres, ratings, reviews, author);
    }

    public FullBookDTO toFullBookDTO(Book book) {
        Set<String> genres = book.getGenres().stream().map(Genre::getGenreType).collect(Collectors.toSet());

        Set<BookRatingDTO> ratings = new HashSet<>();
        for(BookRating bookRating : book.getRatings()) {
            ratings.add(new BookRatingDTO(bookRating.getRating(), bookRating.getCustomer().getUsername()));
        }

        Set<BookReviewDTO> reviews = new HashSet<>();
        for(Review review : book.getReviews()) {
            Map<LikeType, Integer> likes = new HashMap<>();

            for(LikeType likeType: LikeType.values()){
                likes.put(likeType, 0);
            }

            for(ReviewLike reviewLike: review.getReviewLikes()){
                likes.put(reviewLike.getLikeType(), likes.get(reviewLike.getLikeType()) + 1);
            }

            Set<BookReviewCommentDTO> comments = new HashSet<>();
            for(ReviewComment reviewComment: review.getReviewComments()){
                comments.add(new BookReviewCommentDTO(reviewComment.getComment(), reviewComment.getCustomer().getUsername()));
            }
            reviews.add(new BookReviewDTO(review.getTitle(), review.getDescription(), review.getPostDate(), likes, comments));
        }

        return new FullBookDTO(book.getIsbn(), book.getTitle(), book.getDescription(), book.getLaunchDate(),
                book.getTotalPageNumbers(), book.getPublisher().getName(), genres, book.getAuthor().getUsername(), ratings, reviews);
    }

}

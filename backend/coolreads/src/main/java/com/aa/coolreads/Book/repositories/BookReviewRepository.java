package com.aa.coolreads.Book.repositories;

import com.aa.coolreads.Book.models.Review;
import com.aa.coolreads.Book.models.ReviewComment;
import com.aa.coolreads.Book.models.ReviewId;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface BookReviewRepository extends JpaRepository<Review, ReviewId> {

    /*
    @Modifying
    @Query("DELETE FROM Customer ")
    void deleteReview(Book book, Customer customer);

     */

    @Query("SELECT r FROM Review r WHERE r.book.isbn = :isbn")
    Page<Review> findByIsbn(@Param("isbn") String isbn, PageRequest pageable);

    @Query("SELECT rc FROM ReviewComment rc WHERE rc.review.book.isbn = :isbn AND rc.customer.username = :customer_username")
    Page<ReviewComment> findCommentByReview(String isbn, String customer_username, PageRequest pageable);

    @Query(value = "SELECT COALESCE(COUNT(*), 0) FROM ReviewComment rc WHERE rc.review = :review")
    int getReviewCommentSize(Review review);

}


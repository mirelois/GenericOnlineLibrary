package com.aa.coolreads.Book.repositories;

import com.aa.coolreads.Book.models.Book;
import com.aa.coolreads.Book.models.Review;
import com.aa.coolreads.Book.models.ReviewComment;
import com.aa.coolreads.Book.models.ReviewCommentId;
import com.aa.coolreads.User.models.Customer;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface BookReviewCommentRepository extends JpaRepository<ReviewComment, ReviewCommentId> {

    @Query("SELECT rc FROM ReviewComment rc WHERE rc.review.book = :book AND rc.review.customer = :customer")
    Page<ReviewComment> findCommentByReview(Book book, Customer customer, Pageable pageable);

    @Query(value = "SELECT COALESCE(COUNT(*), 0) FROM ReviewComment rc WHERE rc.review = :review")
    int getReviewCommentSize(Review review);

}

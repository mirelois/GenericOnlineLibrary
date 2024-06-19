package com.aa.coolreads.Book.repositories;

import com.aa.coolreads.Book.models.Review;
import com.aa.coolreads.Book.models.ReviewId;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface BookReviewRepository extends JpaRepository<Review, ReviewId> {

    @Query("SELECT r FROM Review r WHERE r.book.isbn = :isbn")
    Page<Review> findByIsbn(@Param("isbn") String isbn, PageRequest pageable);

    @Query(value = "SELECT COUNT(*) FROM Review r WHERE r.book.isbn = :isbn")
    Long getReviewCountByIsbn(String isbn);
}


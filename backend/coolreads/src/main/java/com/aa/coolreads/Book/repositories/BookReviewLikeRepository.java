package com.aa.coolreads.Book.repositories;

import com.aa.coolreads.Book.models.ReviewLike;
import com.aa.coolreads.Book.models.ReviewLikeId;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookReviewLikeRepository extends JpaRepository<ReviewLike, ReviewLikeId> {
}

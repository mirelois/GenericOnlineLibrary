package com.aa.coolreads.Book.repositories;

import com.aa.coolreads.Book.models.ReviewComment;
import com.aa.coolreads.Book.models.ReviewCommentId;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookReviewCommentRepository extends JpaRepository<ReviewComment, ReviewCommentId> {
}

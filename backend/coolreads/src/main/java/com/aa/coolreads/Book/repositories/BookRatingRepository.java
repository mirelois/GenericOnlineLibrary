package com.aa.coolreads.Book.repositories;

import com.aa.coolreads.Book.models.Rating;
import com.aa.coolreads.Book.models.RatingId;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookRatingRepository extends JpaRepository<Rating, RatingId> {
}

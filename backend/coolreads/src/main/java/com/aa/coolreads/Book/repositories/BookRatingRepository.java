package com.aa.coolreads.Book.repositories;

import com.aa.coolreads.Book.models.Rating;
import com.aa.coolreads.Book.models.RatingId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface BookRatingRepository extends JpaRepository<Rating, RatingId> {

    @Query(value = "SELECT COALESCE(AVG(r.rating), 0) FROM Rating r WHERE r.book.isbn = :isbn")
    double getBookAverageRating(@Param("isbn") String isbn);
}

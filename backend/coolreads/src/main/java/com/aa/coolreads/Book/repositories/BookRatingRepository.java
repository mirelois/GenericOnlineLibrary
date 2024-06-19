package com.aa.coolreads.Book.repositories;

import com.aa.coolreads.Book.dto.RatingAvgAndCountDTO;
import com.aa.coolreads.Book.models.Rating;
import com.aa.coolreads.Book.models.RatingId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface BookRatingRepository extends JpaRepository<Rating, RatingId> {

    @Query(value = "SELECT new com.aa.coolreads.Book.dto.RatingAvgAndCountDTO(COALESCE(AVG(r.rating), 0), COUNT(*)) FROM Rating r WHERE r.book.isbn = :isbn")
    RatingAvgAndCountDTO getBookAverageRating(@Param("isbn") String isbn);

    @Query(value = "SELECT r.rating FROM Rating r where r.book.isbn = :isbn AND r.customer.username = :username")
    Optional<Double> findByBookIsbnAndUsername(String isbn, String username);
}

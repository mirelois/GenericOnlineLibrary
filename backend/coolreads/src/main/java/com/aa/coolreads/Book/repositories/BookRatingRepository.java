package com.aa.coolreads.Book.repositories;

import com.aa.coolreads.Book.models.BookRating;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

public interface BookRatingRepository extends CrudRepository<BookRating, String> {
    @Modifying
    @Query("UPDATE BookRating br SET br.rating = :newEmail WHERE br.book.isbn = :isbn AND br.customer.username = :username")
    void updateUserEmailByNumber(double newRating, String isbn, String username);
}

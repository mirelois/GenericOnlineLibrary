package com.aa.coolreads.Book.repositories;

import com.aa.coolreads.Book.models.Book;
import com.aa.coolreads.Book.models.BookRating;
import com.aa.coolreads.User.models.Customer;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

public interface BookRatingRepository extends CrudRepository<BookRating, String> {
    @Modifying
    @Query("UPDATE BookRating br SET br.rating = :newRating WHERE br.book = :book AND br.customer = :customer")
    void updateRating(double newRating, Book book, Customer customer);

    @Modifying
    @Query("DELETE FROM BookRating br WHERE br.book = :book AND br.customer = :customer")
    void deleteRating(Book book, Customer customer);
}

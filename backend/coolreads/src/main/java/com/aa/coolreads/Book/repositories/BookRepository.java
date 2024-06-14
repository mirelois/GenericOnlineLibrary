package com.aa.coolreads.Book.repositories;

import com.aa.coolreads.Book.models.Book;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;


@Repository
public interface BookRepository extends JpaRepository<Book, String> {
    @Query("SELECT b FROM Book b WHERE b.title LIKE CONCAT('%',:title,'%')")
    Page<Book> findBooksByTitle(@Param("title") String title, PageRequest pageable);

    @Query("SELECT b FROM Book b JOIN b.genres g WHERE g = :genre")
    Page<Book> findBooksByGenre(@Param("genre") String genre, PageRequest pageable);
}

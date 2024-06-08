package com.aa.coolreads.Book.repositories;

import com.aa.coolreads.Book.models.Book;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;


public interface BookRepository extends JpaRepository<Book, String> {
    @Query("SELECT b FROM Book b where b.title LIKE CONCAT('%',:title,'%')")
    Page<Book> findBooksByTitle(@Param("title") String title, PageRequest pageable);
}

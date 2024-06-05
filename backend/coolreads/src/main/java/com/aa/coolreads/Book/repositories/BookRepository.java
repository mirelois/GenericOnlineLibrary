package com.aa.coolreads.Book.repositories;

import com.aa.coolreads.Book.models.Book;
import com.aa.coolreads.Book.models.Review;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;


public interface BookRepository extends JpaRepository<Book, String> {
}

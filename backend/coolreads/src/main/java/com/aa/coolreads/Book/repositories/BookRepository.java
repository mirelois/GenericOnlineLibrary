package com.aa.coolreads.Book.repositories;

import com.aa.coolreads.Book.models.Book;
import org.springframework.data.repository.CrudRepository;

public interface BookRepository extends CrudRepository<Book, String> {
}

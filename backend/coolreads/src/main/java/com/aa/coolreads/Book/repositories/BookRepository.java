package com.aa.coolreads.Book.repositories;

import com.aa.coolreads.Book.models.Book;
import com.aa.coolreads.User.models.Customer;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

public interface BookRepository extends CrudRepository<Book, String> {
}

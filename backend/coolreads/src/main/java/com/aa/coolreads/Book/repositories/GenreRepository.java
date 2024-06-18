package com.aa.coolreads.Book.repositories;

import com.aa.coolreads.Book.models.Book;
import com.aa.coolreads.Book.models.Genre;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface GenreRepository extends CrudRepository<Genre, String> {
}

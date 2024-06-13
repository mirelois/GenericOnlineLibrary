package com.aa.coolreads.Book.repositories;

import com.aa.coolreads.Book.models.Genre;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GenreRepository extends CrudRepository<Genre, String> {
}

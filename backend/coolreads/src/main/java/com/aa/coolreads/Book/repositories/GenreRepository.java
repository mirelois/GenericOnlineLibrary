package com.aa.coolreads.Book.repositories;

import com.aa.coolreads.Book.models.Genre;
import org.springframework.data.repository.CrudRepository;

public interface GenreRepository extends CrudRepository<Genre, String> {
}

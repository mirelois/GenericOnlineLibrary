package com.aa.coolreads.User.repositories;

import com.aa.coolreads.User.models.Author;
import org.springframework.data.repository.CrudRepository;

public interface AuthorRepository extends CrudRepository<Author, String> {
}

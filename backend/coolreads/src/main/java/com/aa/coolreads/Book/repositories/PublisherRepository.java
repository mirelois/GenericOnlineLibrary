package com.aa.coolreads.Book.repositories;

import com.aa.coolreads.Book.models.Publisher;
import org.springframework.data.repository.CrudRepository;

public interface PublisherRepository extends CrudRepository<Publisher, String> {
}

package com.aa.coolreads.User.repositories;

import com.aa.coolreads.User.models.Bookshelf;
import com.aa.coolreads.User.models.PersonalBook;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface PersonalBooksRepository extends JpaRepository<PersonalBook, Long> {

    @Query("SELECT pb FROM PersonalBook pb WHERE pb.bookshelf = :bookshelf")
    Page<PersonalBook> findBooks(Bookshelf bookshelf, PageRequest pageable);
}

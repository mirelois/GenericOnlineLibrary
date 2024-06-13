package com.aa.coolreads.User.repositories;

import com.aa.coolreads.User.models.Bookshelf;
import com.aa.coolreads.User.models.PersonalBook;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Set;

@Repository
public interface PersonalBooksRepository extends JpaRepository<PersonalBook, Long> {

    @Query(value = "SELECT pb FROM PersonalBook pb WHERE pb.bookshelf = :bookshelf")
    Set<PersonalBook> findBooks(Bookshelf bookshelf);

    @Modifying
    @Query("DELETE FROM PersonalBook pb WHERE pb.bookshelf = :bookshelf AND pb.book.isbn = :isbn")
    void deletePersonalBookByIsbnAndBookshelf(String isbn, Bookshelf bookshelf);

    @Query(value = "SELECT COUNT(*) FROM PersonalBook pb WHERE pb.bookshelf = :bookshelf")
    Integer getBooksSize(Bookshelf bookshelf);
}

package com.aa.coolreads.User.repositories;

import com.aa.coolreads.Book.models.Slice;
import com.aa.coolreads.User.models.Bookshelf;
import com.aa.coolreads.User.models.PersonalBook;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Set;

public interface PersonalBooksRepository extends JpaRepository<PersonalBook, Long> {

    @Query(value = "SELECT pb FROM PersonalBook pb WHERE pb.bookshelf = :bookshelf")
    Set<PersonalBook> findBooks(Bookshelf bookshelf);

    @Modifying
    @Query("DELETE FROM PersonalBook pb WHERE pb.bookshelf = :bookshelf AND pb.book.isbn = :isbn")
    void deletePersonalBookByIsbnAndBookshelf(String isbn, Bookshelf bookshelf);

    @Query(value = "SELECT COUNT(*) FROM PersonalBook pb WHERE pb.bookshelf = :bookshelf")
    Integer getBooksSize(Bookshelf bookshelf);

    @Query(value = "SELECT COUNT(*) FROM PersonalBook pb WHERE pb.bookshelf.name = :bookshelf AND pb.book.isbn = :isbn")
    Integer getBooksSizeByBookShelfName(String bookshelf, String isbn);

    @Query(value = "SELECT pb.bookshelf.customer.country, COUNT(*) FROM PersonalBook pb WHERE pb.bookshelf.name = :bookshelf GROUP BY pb.bookshelf.customer.country")
    List<Slice> getCountrySlicesByBookshelfName(String bookshelf, String country);


}

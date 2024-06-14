package com.aa.coolreads.User.repositories;

import com.aa.coolreads.Book.dto.SliceDTO;
import com.aa.coolreads.Book.models.Book;
import com.aa.coolreads.User.models.Bookshelf;
import com.aa.coolreads.User.models.PersonalBook;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.Set;

@Repository
public interface PersonalBooksRepository extends JpaRepository<PersonalBook, Long> {

    @Query(value = "SELECT pb FROM PersonalBook pb WHERE pb.bookshelf = :bookshelf")
    Set<PersonalBook> findBooks(Bookshelf bookshelf);

    @Query("SELECT pb FROM PersonalBook pb WHERE pb.bookshelf = :bookshelf AND pb.book = :book")
    Optional<PersonalBook> getPersonalBookByBookAndBookshelf(Book book, Bookshelf bookshelf);

    @Modifying
    @Query("DELETE FROM PersonalBook pb WHERE pb.bookshelf = :bookshelf AND pb.book.isbn = :isbn")
    void deletePersonalBookByIsbnAndBookshelf(String isbn, Bookshelf bookshelf);

    @Query(value = "SELECT COUNT(*) FROM PersonalBook pb WHERE pb.bookshelf = :bookshelf")
    Integer getBooksSize(Bookshelf bookshelf);

    @Query(value = "SELECT COUNT(*) FROM PersonalBook pb WHERE pb.bookshelf.name = :bookshelf AND pb.book.isbn = :isbn")
    Integer getBooksSizeByBookShelfName(String bookshelf, String isbn);

    @Query(value = "SELECT pb.bookshelf.customer.country, COUNT(*) FROM PersonalBook pb WHERE pb.bookshelf.name = :bookshelf AND pb.book.isbn = :isbn GROUP BY pb.bookshelf.customer.country")
    List<SliceDTO> getCountrySlicesByBookshelfName(String bookshelf, String isbn);

    @Query(value = "SELECT year(current_date) - year(pb.bookshelf.customer.birthDate) FROM PersonalBook pb WHERE pb.bookshelf.name = :bookshelf AND pb.book.isbn = :isbn")
    List<Integer> getAgesByBookshelfName(String bookshelf, String isbn);

    @Query(value = "select pb.bookshelf.customer.gender, count(*) from PersonalBook pb where pb.bookshelf.name = :bookshelf and pb.book.isbn = :isbn group by pb.bookshelf.customer.gender")
    List<SliceDTO> getGenderSlicesByBookshelfName(String bookshelf, String isbn);

    //@Query(value = "SELECT count(*) from PersonalBook pb where pb.bookshelf.name = :bookshelf and pb.book.isbn = :isbn")
}


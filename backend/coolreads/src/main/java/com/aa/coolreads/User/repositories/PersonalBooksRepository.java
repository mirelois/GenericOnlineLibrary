package com.aa.coolreads.User.repositories;

import com.aa.coolreads.Book.dto.SliceDTO;
import com.aa.coolreads.Book.dto.SliceInterfaceDTO;
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

    @Query(value = "SELECT pb.bookshelf.customer.profileDetails.country AS className , COUNT(*) AS amount FROM PersonalBook pb WHERE pb.bookshelf.name = :bookshelf AND pb.book.isbn = :isbn GROUP BY pb.bookshelf.customer.profileDetails.country")
    List<SliceInterfaceDTO> getCountrySlicesByBookshelfName(String bookshelf, String isbn);

    @Query(nativeQuery = true, value = """
        with ages as (
        select customer.username, extract('YEAR' from AGE(CURRENT_DATE, customer.birth_date)) as age
        from personal_book
        inner join bookshelf on bookshelf.id = bookshelf_id
        inner join customer on bookshelf.customer_username = customer.username
        where book_isbn = :isbn and bookshelf.name = :bookshelf)
        select ageClass as className, amount
        from ageRange
        inner join (
            select width_bucket(age, array[0,13,18,31,61]) as bucket, count(*) as amount
            from ages
            group by bucket
        )
        on ageRange.id=bucket
        """)
    List<SliceInterfaceDTO> getAgesByBookshelfName(String bookshelf, String isbn);

    @Query(value = "select pb.bookshelf.customer.profileDetails.gender as className, count(*) as amount from PersonalBook pb where pb.bookshelf.name = :bookshelf and pb.book.isbn = :isbn group by pb.bookshelf.customer.profileDetails.gender")
    List<SliceInterfaceDTO> getGenderSlicesByBookshelfName(String bookshelf, String isbn);

    //@Query(value = "SELECT date_trunc(:timeframe, pb.insertDate) as className, count(*) as amount from PersonalBook pb where pb.bookshelf.name = :bookshelf and pb.book.isbn = :isbn group by className")
    //Page<SliceInterfaceDTO> getTimeLine(String bookshelf, String isbn, String timeframe, PageRequest pageable);
}


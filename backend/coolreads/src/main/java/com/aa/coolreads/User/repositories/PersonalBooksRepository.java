package com.aa.coolreads.User.repositories;

import com.aa.coolreads.Book.dto.SliceInterfaceDTO;
import com.aa.coolreads.Book.models.Book;
import com.aa.coolreads.User.models.*;
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

    @Query(value = "SELECT pb FROM PersonalBook pb JOIN pb.bookshelves bs WHERE bs = :bookshelf")
    Set<PersonalBook> findBooks(Bookshelf bookshelf);

    @Query("SELECT pb FROM PersonalBook pb WHERE pb.book = :book AND pb.customer = :customer")
    Optional<PersonalBook> getPersonalBookByBookAndCustomer(Book book, Customer customer);

    @Query("SELECT COUNT(*) > 0 FROM PersonalBook pb JOIN pb.bookshelves pbbs WHERE pb.customer = :customer AND pb = :personalBook AND pbbs.exclusivityClass = :exclusivityClass")
    boolean checkIfPersonalBookHasConflict(Customer customer, PersonalBook personalBook, ExclusivityClass exclusivityClass);

    @Modifying
    @Query("DELETE FROM PersonalBook pb WHERE pb.customer = :customer AND pb.book.isbn = :isbn")
    void deletePersonalBookByIsbnAndCustomer(String isbn, Customer customer);

    @Query(value = "SELECT COUNT(*) FROM PersonalBook pb WHERE pb.customer = :customer")
    Integer getBookSizeByCustomer(Customer customer);

    @Query(value = "SELECT COUNT(*) FROM PersonalBook pb JOIN pb.bookshelves bs WHERE bs = :bookshelf")
    Integer getBooksSize(Bookshelf bookshelf);

    @Query(value = "SELECT COUNT(*) FROM PersonalBook pb JOIN pb.bookshelves bs WHERE bs.name = :bookshelf AND pb.book.isbn = :isbn")
    Integer getBooksSizeByBookShelfName(String bookshelf, String isbn);

    @Query(value = "SELECT bs.customer.profileDetails.country AS className , COUNT(*) AS amount FROM PersonalBook pb JOIN pb.bookshelves bs WHERE bs.name = :bookshelf AND pb.book.isbn = :isbn GROUP BY bs.customer.profileDetails.country")
    List<SliceInterfaceDTO> getCountrySlicesByBookshelfName(String bookshelf, String isbn);

    @Query(nativeQuery = true, value = """
        with ages as (
        select customer.username, extract('YEAR' from AGE(CURRENT_DATE, customer.birth_date)) as age
        from personal_book
        inner join bookshelf_personalbook bp on personal_book.id = bp.personalbook_id
        inner join bookshelf on bookshelf.id = bp.bookshelf_id
        inner join customer on bookshelf.customer_username = customer.username
        where book_isbn = :isbn and bookshelf.name = :bookshelf)
        select ageclass as className, amount
        from agerange
        inner join (
            select width_bucket(age, array[0,13,18,31,61]) as bucket, count(*) as amount
            from ages
            group by bucket
        )
        on agerange.id=bucket
        """)
    List<SliceInterfaceDTO> getAgesByBookshelfName(String bookshelf, String isbn);

    @Query(value = "select bs.customer.profileDetails.gender as className, count(*) as amount from PersonalBook pb JOIN pb.bookshelves bs where bs.name = :bookshelf and pb.book.isbn = :isbn group by bs.customer.profileDetails.gender")
    List<SliceInterfaceDTO> getGenderSlicesByBookshelfName(String bookshelf, String isbn);

    @Query(value = "SELECT date_trunc(:timeframe, pb.insertDate) as className, count(*) as amount from PersonalBook pb JOIN pb.bookshelves bs WHERE bs.name = :bookshelf and pb.book.isbn = :isbn group by pb.insertDate")
    Page<SliceInterfaceDTO> getTimeLine(String bookshelf, String isbn, String timeframe, PageRequest pageable);
}


package com.aa.coolreads.User.repositories;

import com.aa.coolreads.Book.models.AgeSlice;
import com.aa.coolreads.Book.models.CountrySlice;
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
    List<CountrySlice> getCountrySlicesByBookshelfName(String bookshelf, String isbn);

    @Query(value = "with ages as (\n" +
            "    select\n" +
            "        extract('YEAR' from AGE(CURRENT_DATE, pb.customer.birth_date)) as age\n" +
            "            from PersonalBook pb where pb.bookshelf.name = :bookshelf and pb.book.isbn = :isbn)\n" +
            "    select ageClass, amount from ageRange\n" +
            "    inner join (\n" +
            "    select\n" +
            "        width_bucket(age, array[0,13,18,31,61]) as bucket, count(*) as amount\n" +
            "        from ages\n" +
            "        group by bucket\n" +
            "    ) on ageRange.id=bucket;\n")
    List<AgeSlice> getAgeSliceByBookshelfName(String bookshelf, String isbn);

}

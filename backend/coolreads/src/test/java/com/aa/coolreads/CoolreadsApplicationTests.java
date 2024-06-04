package com.aa.coolreads;

import com.aa.coolreads.Book.dto.BookDTO;
import com.aa.coolreads.Book.dto.FullBookDTO;
import com.aa.coolreads.Book.exception.BookAlreadyExistsException;
import com.aa.coolreads.Book.exception.BookNotFoundException;
import com.aa.coolreads.Book.exception.GenresNotFoundException;
import com.aa.coolreads.Book.exception.PublisherNotFoundException;
import com.aa.coolreads.Book.services.BookService;
import com.aa.coolreads.User.exception.AuthorNotFoundException;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Date;
import java.util.HashSet;
import java.util.Objects;

@SpringBootTest
class CoolreadsApplicationTests {

    @Autowired
    private BookService bookService;

	@Test
	void contextLoads() {
	}

    @Test
    void testInsertBook() {

        HashSet<String> genres = new HashSet<>();

        genres.add("romance");
        genres.add("Adventure");
        genres.add("SciFi");

        BookDTO bookDTO = new BookDTO(
                "978-3-16-148410-0",
                "this is a book",
                "this is a book description",
                new Date(2024, 12, 20),
                331,
                "TechBooks Publishing",
                genres,
                "jkrowling",
                "a"
        );

        try {
            bookService.insertBook(bookDTO);
        } catch (BookAlreadyExistsException e) {
            throw new RuntimeException(e);
        } catch (PublisherNotFoundException e) {
            throw new RuntimeException(e);
        } catch (GenresNotFoundException e) {
            throw new RuntimeException(e);
        } catch (AuthorNotFoundException e) {
            throw new RuntimeException(e);
        }

    }

	@Test
	void testBookService() {

        try {
            FullBookDTO fullBookDTO = bookService.getBookByISBN("1");
            assert Objects.equals(fullBookDTO.getPublisherName(), "Science Today Press");
        } catch (BookNotFoundException e) {
            e.printStackTrace();
        }

    }
}


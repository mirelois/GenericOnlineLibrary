package com.aa.coolreads.Book.components;

import com.aa.coolreads.Book.dto.BookDTO;
import com.aa.coolreads.Book.models.Book;
import com.aa.coolreads.Book.models.Genre;
import com.aa.coolreads.Book.models.Publisher;
import org.springframework.stereotype.Component;

import java.util.Set;
import java.util.stream.Collectors;

@Component
public class BookMapper {
    public Book toBook(BookDTO bookDTO, Publisher publisher, Set<Genre> genres) {
        return new Book(bookDTO.getIsbn(), bookDTO.getTitle(), bookDTO.getDescription(),
                bookDTO.getLaunchDate(), bookDTO.getTotalPageNumbers(),
                publisher, genres);
    }

    public BookDTO toBookDTO(Book book) {
        Set<String> genres = book.getGenres().stream().map(Genre::getGenreType).collect(Collectors.toSet());

        return new BookDTO(book.getIsbn(), book.getTitle(), book.getDescription(), book.getLaunchDate(),
                book.getTotalPageNumbers(), book.getPublisher().getName(), genres);
    }
}

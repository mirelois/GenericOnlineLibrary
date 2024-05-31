package com.aa.coolreads.Book.services;

import com.aa.coolreads.Book.dto.BookDTO;
import com.aa.coolreads.Book.exception.BookNotFoundException;
import com.aa.coolreads.Book.models.Book;
import com.aa.coolreads.Book.models.Genre;
import com.aa.coolreads.Book.repositories.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class BookService {
    private final BookRepository bookRepository;

    @Autowired
    public BookService(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    public BookDTO getBookByISBN(String isbn) throws BookNotFoundException {
        Optional<Book> bookOptional = this.bookRepository.findById(isbn);
        if(bookOptional.isPresent()){
            Book book = bookOptional.get();
            String publisherName = book.getPublisher().getName();
            Set<String> genres = book.getGenres().stream()
                    .map(genre -> genre.getGenreType().name())
                    .collect(Collectors.toSet());

            return new BookDTO(book.getIsbn(), book.getTitle(), book.getDescription(),
                    book.getLaunchDate(), book.getTotalPageNumbers(), publisherName, genres);
        }

        throw new BookNotFoundException("Book Not Found");
    }
}

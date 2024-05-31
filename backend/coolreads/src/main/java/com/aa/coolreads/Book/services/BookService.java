package com.aa.coolreads.Book.services;

import com.aa.coolreads.Book.dto.BookDTO;
import com.aa.coolreads.Book.exception.BookNotFoundException;
import com.aa.coolreads.Book.models.Book;
import com.aa.coolreads.Book.repositories.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

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
            return new BookDTO(bookOptional.get());
        }

        throw new BookNotFoundException("Book with isbn '" + isbn + "' Not Found");
    }
}

package com.aa.coolreads.Book.services;

import com.aa.coolreads.Book.components.BookMapper;
import com.aa.coolreads.Book.dto.BookDTO;
import com.aa.coolreads.Book.exception.BookAlreadyExistsException;
import com.aa.coolreads.Book.exception.BookNotFoundException;
import com.aa.coolreads.Book.models.Book;
import com.aa.coolreads.Book.models.Publisher;
import com.aa.coolreads.Book.repositories.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class BookService {
    private final BookRepository bookRepository;

    private final BookMapper bookMapper;

    @Autowired
    public BookService(BookRepository bookRepository, BookMapper bookMapper) {
        this.bookRepository = bookRepository;
        this.bookMapper = bookMapper;
    }

    public BookDTO getBookByISBN(String isbn) throws BookNotFoundException {
        Optional<Book> bookOptional = this.bookRepository.findById(isbn);
        if(bookOptional.isPresent()){
            return this.bookMapper.toBookDTO(bookOptional.get());
        }

        throw new BookNotFoundException("Book with isbn '" + isbn + "' Not Found");
    }

    public void insertBook(BookDTO bookDTO) throws BookAlreadyExistsException{
        String isbn = bookDTO.getIsbn();
        if(this.bookRepository.findById(isbn).isPresent()){
            throw new BookAlreadyExistsException(isbn);
        }

        Optional<Publisher> publisherOptional = this.bookRepository.findById(bookDTO.getPublisherName());

        this.bookRepository.save(this.bookMapper.toBook(bookDTO, ));
    }
}

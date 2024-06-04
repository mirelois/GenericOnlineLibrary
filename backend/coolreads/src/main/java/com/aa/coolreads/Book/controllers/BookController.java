package com.aa.coolreads.Book.controllers;

import com.aa.coolreads.Book.dto.BookDTO;
import com.aa.coolreads.Book.dto.BookRatingDTO;
import com.aa.coolreads.Book.dto.FullBookDTO;
import com.aa.coolreads.Book.exception.BookAlreadyExistsException;
import com.aa.coolreads.Book.exception.BookNotFoundException;
import com.aa.coolreads.Book.exception.GenresNotFoundException;
import com.aa.coolreads.Book.exception.PublisherNotFoundException;
import com.aa.coolreads.Book.services.BookService;
import com.aa.coolreads.User.exception.AuthorNotFoundException;
import com.aa.coolreads.User.exception.CustomerNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

@RestController
@RequestMapping(value = "/book")
public class BookController {
    private final BookService bookService;

    @Autowired
    public BookController(BookService bookService){
        this.bookService = bookService;
    }

    @GetMapping("/{isbn}")
    public FullBookDTO getBook(@PathVariable String isbn){
        try{
            return bookService.getBookByISBN(isbn);
        } catch (BookNotFoundException e){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage());
        }
    }

    @PostMapping
    public void insertBook(@RequestBody BookDTO bookDTO){
        try{
            bookService.insertBook(bookDTO);
        } catch (BookAlreadyExistsException | PublisherNotFoundException | GenresNotFoundException |
                 AuthorNotFoundException e){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
        }
    }

    @PostMapping("/{isbn}/rate")
    public void rateBook(@PathVariable String isbn, @RequestBody BookRatingDTO bookRatingDTO){
        try{
            this.bookService.insertRating(isbn, bookRatingDTO);
        } catch (BookNotFoundException | CustomerNotFoundException e){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage());
        }
    }

    @PatchMapping("/{isbn}/rate")
    public void UpdateBookRating(@PathVariable String isbn, @RequestBody BookRatingDTO bookRatingDTO){
        try{
            this.bookService.updateRating(isbn, bookRatingDTO);
        } catch (BookNotFoundException | CustomerNotFoundException e){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage());
        }
    }

    @DeleteMapping("/{isbn}/rate")
    public void DeleteRateBook(@PathVariable String isbn, @RequestParam String customerUsername){
        try{
            this.bookService.deleteRating(isbn, customerUsername);
        } catch (BookNotFoundException | CustomerNotFoundException e){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage());
        }
    }
}

package com.aa.coolreads.Book.controllers;

import com.aa.coolreads.Book.dto.*;
import com.aa.coolreads.Book.exception.*;
import com.aa.coolreads.Book.services.BookService;
import com.aa.coolreads.User.exception.AuthorNotFoundException;
import com.aa.coolreads.User.exception.CustomerNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.Set;

@RestController
@RequestMapping(value = "/api/book")
public class BookController {
    private final BookService bookService;

    @Autowired
    public BookController(BookService bookService){
        this.bookService = bookService;
    }

    @GetMapping("/name")
    public Set<BookDTO> getBooksByName(@RequestParam String title){
        try{
            return bookService.findBooksByTitle(title);
        } catch (BookNotFoundException e){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage());
        }
    }

    @GetMapping("/genre")
    public ResponseEntity<?> getBooksByGenre(@RequestParam String genre){
        try{
            return ResponseEntity.ok().body(bookService.findBooksByGenre(genre));
        } catch (GenresNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
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
                 AuthorNotFoundException | InvalidISBNExeption e){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
        }
    }

    @PreAuthorize("#username == principal.username")
    @GetMapping("/{isbn}/rate")
    public RatingDTO getBookRating(@PathVariable String isbn, @RequestParam String username){
        try {
            return this.bookService.getRating(isbn, username);
        } catch (RatingNotFoundException e){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage());
        }
    }

    @PreAuthorize("#username == principal.username")
    @PostMapping("/{isbn}/rate")
    public void insertBookRating(@PathVariable String isbn, @RequestParam String username, @RequestParam Double rating){
        try{
            this.bookService.insertRating(isbn, username, rating);
        } catch (BookNotFoundException | CustomerNotFoundException e){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage());
        } catch (InvalidRatingException e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
        }
    }

    @PreAuthorize("#username == principal.username")
    @PatchMapping("/{isbn}/rate")
    public void UpdateBookRating(@PathVariable String isbn, @RequestParam String username, @RequestParam Double rating){
        try{
            this.bookService.updateRating(isbn, username, rating);
        } catch (BookNotFoundException | CustomerNotFoundException | RatingNotFoundException e){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage());
        } catch (InvalidRatingException e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
        }
    }

    @PreAuthorize("#username == principal.username")
    @DeleteMapping("/{isbn}/rate")
    public void DeleteBookRating(@PathVariable String isbn, @RequestParam String username){
        try{
            this.bookService.deleteRating(isbn, username);
        } catch (BookNotFoundException | CustomerNotFoundException e){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage());
        }
    }
}

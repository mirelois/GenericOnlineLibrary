package com.aa.coolreads.Book.controllers;

import com.aa.coolreads.Book.dto.*;
import com.aa.coolreads.Book.exception.*;
import com.aa.coolreads.Book.services.BookService;
import com.aa.coolreads.User.exception.AuthorNotFoundException;
import com.aa.coolreads.User.exception.CustomerNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.Set;

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
                 AuthorNotFoundException | InvalidISBNExeption e){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
        }
    }

    @PostMapping("/{isbn}/review")
    public void reviewBook(@PathVariable String isbn, @RequestParam String username, @RequestBody SimpleReviewDTO simpleReviewDTO){
        try{
            this.bookService.insertReview(isbn, username, simpleReviewDTO);
        } catch (BookNotFoundException | CustomerNotFoundException e){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage());
        } catch (InvalidRatingException e){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
        }
    }

    @GetMapping("/{isbn}/review")
    public Set<BookReviewDTO> getReview(@PathVariable String isbn, @RequestParam Integer page, @RequestParam Integer size){
        return this.bookService.getReviews(isbn, page, size);
    }

    @GetMapping("/{isbn}/review/comment")
    public Set<BookReviewCommentDTO> getReviewComment(@PathVariable String isbn, @RequestParam String review_username, @RequestParam Integer page, @RequestParam Integer size){
        return this.bookService.getReviewComments(isbn, review_username, page, size);
    }

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

    @PatchMapping("/{isbn}/rate")
    public void UpdateBookRating(@PathVariable String isbn, @RequestParam String username, @RequestParam Double rating){
        try{
            this.bookService.updateRating(isbn, username, rating);
        } catch (BookNotFoundException | CustomerNotFoundException | ReviewNotFoundException e){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage());
        } catch (InvalidRatingException e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
        }
    }

    @DeleteMapping("/{isbn}/rate")
    public void DeleteRateBook(@PathVariable String isbn, @RequestParam String username){
        try{
            this.bookService.deleteRating(isbn, username);
        } catch (BookNotFoundException | CustomerNotFoundException | ReviewNotFoundException e){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage());
        }
    }
}

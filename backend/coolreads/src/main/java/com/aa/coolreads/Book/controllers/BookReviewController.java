package com.aa.coolreads.Book.controllers;

import com.aa.coolreads.Book.dto.BookReviewCommentDTO;
import com.aa.coolreads.Book.dto.BookReviewDTO;
import com.aa.coolreads.Book.dto.SimpleReviewDTO;
import com.aa.coolreads.Book.exception.BookNotFoundException;
import com.aa.coolreads.Book.exception.InsufficientReviewParametersException;
import com.aa.coolreads.Book.exception.ReviewNotFoundException;
import com.aa.coolreads.Book.services.BookReviewService;
import com.aa.coolreads.User.exception.CustomerNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.Set;

@RestController
@RequestMapping(value = "/book/{isbn}/review")
public class BookReviewController {

    private final BookReviewService bookReviewService;

    @Autowired
    public BookReviewController(BookReviewService bookReviewService) {
        this.bookReviewService = bookReviewService;
    }

    @GetMapping
    public Set<BookReviewDTO> getReview(@PathVariable String isbn, @RequestParam Integer page, @RequestParam Integer size){
        return this.bookReviewService.getReviews(isbn, page, size);
    }

    @PostMapping
    public void insertReview(@PathVariable String isbn, @RequestParam String username, @RequestBody SimpleReviewDTO simpleReviewDTO){
        try{
            this.bookReviewService.insertReview(isbn, username, simpleReviewDTO);
        } catch (BookNotFoundException | CustomerNotFoundException e){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage());
        }
    }

    @PatchMapping
    public void updateReview(@PathVariable String isbn, @RequestParam String username, @RequestBody SimpleReviewDTO simpleReviewDTO){
        try{
            this.bookReviewService.updateReview(isbn, username, simpleReviewDTO);
        } catch (BookNotFoundException | CustomerNotFoundException | ReviewNotFoundException e){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage());
        }catch (InsufficientReviewParametersException e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
        }
    }

    @DeleteMapping
    public void deleteReview(@PathVariable String isbn, @RequestParam String username){
        try{
            this.bookReviewService.deleteReview(isbn, username);
        } catch (BookNotFoundException | CustomerNotFoundException e){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage());
        }
    }

    @GetMapping("/{review_username}/comment")
    public Set<BookReviewCommentDTO> getReviewComments(@PathVariable String isbn, @PathVariable String review_username, @RequestParam Integer page, @RequestParam Integer size){
        return this.bookReviewService.getReviewComments(isbn, review_username, page, size);
    }

    @PostMapping("/{review_username}/comment")
    public ResponseEntity<String> insertReviewComment(@PathVariable String isbn, @PathVariable String review_username, @RequestBody String comment){
        try{
            this.bookReviewService.insertComment(isbn, review_username, comment);
            return ResponseEntity.ok().build();
        } catch (ReviewNotFoundException | CustomerNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }

    @DeleteMapping("/{review_username}/comment")
    public ResponseEntity<String> deleteReviewComment(@PathVariable String isbn, @PathVariable String review_username){
        try{
            this.bookReviewService.deleteComment(isbn, review_username);
            return ResponseEntity.ok().build();
        } catch (ReviewNotFoundException | CustomerNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }

    @PostMapping("/{review_username}/like")
    public ResponseEntity<String> insertLike(@PathVariable String isbn, @PathVariable String review_username, @RequestBody String likeType){
        try {
            this.bookReviewService.insertLike(isbn, review_username, likeType);
            return ResponseEntity.ok().build();
        } catch (ReviewNotFoundException | CustomerNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }

    @DeleteMapping("/{review_username}/like")
    public ResponseEntity<String> deleteLike(@PathVariable String isbn, @PathVariable String review_username){
        try{
            this.bookReviewService.deleteLike(isbn, review_username);
            return ResponseEntity.ok().build();
        } catch (ReviewNotFoundException | CustomerNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }
}

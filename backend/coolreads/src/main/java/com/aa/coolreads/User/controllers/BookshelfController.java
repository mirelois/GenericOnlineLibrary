package com.aa.coolreads.User.controllers;

import com.aa.coolreads.Book.exception.BookNotFoundException;
import com.aa.coolreads.User.dto.BookShelfCreationDTO;
import com.aa.coolreads.User.dto.PersonalBookDTO;
import com.aa.coolreads.User.dto.SimpleBookShelfDTO;
import com.aa.coolreads.User.exception.*;
import com.aa.coolreads.User.services.BookshelfService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.Set;

@RestController
@RequestMapping(value = "/customer/{username}/bookshelf")
public class BookshelfController {

    private final BookshelfService bookshelfService;

    @Autowired
    public BookshelfController(BookshelfService bookshelfService) {
        this.bookshelfService = bookshelfService;
    }

    @PreAuthorize("#username == principal.username")
    @GetMapping
    public ResponseEntity<Set<SimpleBookShelfDTO>> getBookshelves(@PathVariable String username) {
        try {
            return ResponseEntity.ok().body(this.bookshelfService.getBookshelf(username));
        } catch (CustomerNotFoundException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage());
        }
    }

    @PreAuthorize("#username == principal.username")
    @PostMapping
    public ResponseEntity<String> insertBookshelf(@PathVariable String username, @RequestBody BookShelfCreationDTO bookshelf){
        try {
            this.bookshelfService.insertBookshelf(bookshelf, username);
            return ResponseEntity.ok().build();
        } catch (CustomerNotFoundException e){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage());
        } catch (BookshelfAlreadyExists e) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body(e.getMessage());
        }
    }

    @PreAuthorize("#username == principal.username")
    @PutMapping
    public ResponseEntity<String> updateBookshelf(@PathVariable String username, @RequestBody BookShelfCreationDTO bookshelf){
        try {
            this.bookshelfService.updateBookshelf(bookshelf, username);
            return ResponseEntity.ok().build();
        } catch (CustomerNotFoundException | BookshelfNotFoundException e){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage());
        }
    }

    @PreAuthorize("#username == principal.username")
    @DeleteMapping
    public ResponseEntity<String> deleteBookshelf(@PathVariable String username, @RequestParam String bookshelfName){
        try{
            this.bookshelfService.deleteBookshelf(username, bookshelfName);
            return ResponseEntity.ok().build();
        } catch (CustomerNotFoundException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage());
        } catch (InvalidBookshelfDeletionException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

    @PreAuthorize("#username == principal.username")
    @GetMapping("/{name}")
    public ResponseEntity<Set<PersonalBookDTO>> getBooks(@PathVariable String username, @PathVariable String name){
        try{
            return ResponseEntity.ok().body(this.bookshelfService.getBooks(name, username));
        } catch (BookshelfNotFoundException | CustomerNotFoundException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage());
        }
    }

    @PreAuthorize("#username == principal.username")
    @PostMapping("/{name}")
    public ResponseEntity<String> insertBook(@PathVariable String name, @PathVariable String username, @RequestBody PersonalBookDTO book){
        try{
            this.bookshelfService.insertBook(name, username, book);
            return ResponseEntity.ok().build();
        } catch (BookshelfNotFoundException | BookNotFoundException | CustomerNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        } catch (PersonalBookAlreadyExists e) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body(e.getMessage());
        }
    }

    @PreAuthorize("#username == principal.username")
    @DeleteMapping("/{name}")
    public ResponseEntity<Void> deleteBook(@PathVariable String name, @PathVariable String username, @RequestParam String isbn){
        try{
            this.bookshelfService.deleteBook(name, username, isbn);
            return ResponseEntity.ok().build();
        } catch (BookshelfNotFoundException | CustomerNotFoundException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage());
        }
    }
}

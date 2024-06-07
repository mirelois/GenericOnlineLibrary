package com.aa.coolreads.User.controllers;

import com.aa.coolreads.Book.exception.BookNotFoundException;
import com.aa.coolreads.User.dto.BookShelfCreationDTO;
import com.aa.coolreads.User.dto.PersonalBookDTO;
import com.aa.coolreads.User.exception.BookshelfNotFoundException;
import com.aa.coolreads.User.exception.CustomerNotFoundException;
import com.aa.coolreads.User.services.BookshelfService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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

    @GetMapping
    public ResponseEntity<Set<BookShelfCreationDTO>> getBookshelves(@PathVariable String username) {
        try {
            return ResponseEntity.ok().body(this.bookshelfService.getBookshelf(username));
        } catch (CustomerNotFoundException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage());
        }
    }

    @PostMapping
    public ResponseEntity<Void> insertBookshelf(@PathVariable String username, @RequestBody BookShelfCreationDTO bookshelf){
        try {
            this.bookshelfService.insertBookshelf(bookshelf, username);
            return ResponseEntity.ok().build();
        } catch (CustomerNotFoundException e){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage());
        }
    }

    @GetMapping("/{name}")
    public ResponseEntity<Set<PersonalBookDTO>> getBooks(@PathVariable String username, @PathVariable String name, @RequestParam Integer page, @RequestParam Integer size){
        try{
            return ResponseEntity.ok().body(this.bookshelfService.getBooks(name, username, page, size));
        } catch (BookshelfNotFoundException | CustomerNotFoundException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage());
        }
    }

    @PostMapping("/{name}")
    public ResponseEntity<Void> insertBook(@PathVariable String name, @RequestParam String username, @RequestBody PersonalBookDTO book){
        try{
            this.bookshelfService.insertBook(name, username, book);
            return ResponseEntity.ok().build();
        } catch (BookshelfNotFoundException | BookNotFoundException | CustomerNotFoundException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage());
        }
    }

    @DeleteMapping("/{name}")
    public ResponseEntity<Void> deleteBook(@PathVariable String name, @RequestParam String username, @RequestParam String isbn){
        try{
            this.bookshelfService.deleteBook(name, username, isbn);
            return ResponseEntity.ok().build();
        } catch (BookshelfNotFoundException | CustomerNotFoundException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage());
        }
    }
}

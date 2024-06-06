package com.aa.coolreads.User.controllers;

import com.aa.coolreads.User.dto.BookShelfCreationDTO;
import com.aa.coolreads.User.dto.PersonalBookDTO;
import com.aa.coolreads.User.exception.BookshelfNotFoundException;
import com.aa.coolreads.User.exception.CustomerNotFoundException;
import com.aa.coolreads.User.models.Customer;
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
}

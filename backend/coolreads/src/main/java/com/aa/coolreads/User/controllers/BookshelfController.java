package com.aa.coolreads.User.controllers;

import com.aa.coolreads.Book.exception.BookNotFoundException;
import com.aa.coolreads.User.dto.BookShelfCreationDTO;
import com.aa.coolreads.User.dto.ExclusivityClassDTO;
import com.aa.coolreads.User.dto.PersonalBookDTO;
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
    public ResponseEntity<?> getBookshelves(@PathVariable String username) {
        try {
            return ResponseEntity.ok().body(this.bookshelfService.getBookshelf(username));
        } catch (CustomerNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }

    @PreAuthorize("#username == principal.username")
    @PostMapping
    public ResponseEntity<String> insertBookshelf(@PathVariable String username, @RequestBody BookShelfCreationDTO bookshelf){
        try {
            this.bookshelfService.insertBookshelf(bookshelf, username);
            return ResponseEntity.ok().build();
        } catch (CustomerNotFoundException e){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
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
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }

    @PreAuthorize("#username == principal.username")
    @DeleteMapping
    public ResponseEntity<String> deleteBookshelf(@PathVariable String username, @RequestParam String bookshelfName){
        try{
            this.bookshelfService.deleteBookshelf(username, bookshelfName);
            return ResponseEntity.ok().build();
        } catch (CustomerNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
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
    @GetMapping("/{name}/checkConflicts")
    public ResponseEntity<String> checkIfExclusivityClassAreConflictFree(@PathVariable String name, @PathVariable String username, @RequestParam String isbn){
        try{
            boolean isConflictFree = this.bookshelfService.checkIfExclusivityClassesAreConflictFree(username, isbn, name);
            return ResponseEntity.ok().body(Boolean.toString(isConflictFree));
        } catch (BookshelfNotFoundException | CustomerNotFoundException | BookNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }

    @PreAuthorize("#username == principal.username")
    @PostMapping("/{name}")
    public ResponseEntity<String> insertBook(@PathVariable String name, @PathVariable String username, @RequestParam String isbn){
        try{
            this.bookshelfService.insertBook(name, username, isbn);
            return ResponseEntity.ok().build();
        } catch (BookshelfNotFoundException | BookNotFoundException | CustomerNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        } catch (PersonalBookAlreadyExistsException e) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body(e.getMessage());
        }
    }

    @PreAuthorize("#username == principal.username")
    @PutMapping("/personalBook")
    public ResponseEntity<String> updateBook(@PathVariable String username, @RequestParam String isbn, @RequestParam Integer pagesRead){
        try{
            this.bookshelfService.updateBook(username, isbn, pagesRead);
            return ResponseEntity.ok().build();
        } catch (BookNotFoundException | CustomerNotFoundException | PersonalBookNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        } catch (InvalidPageReadException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

    @PreAuthorize("#username == principal.username")
    @DeleteMapping("/personalBook")
    public ResponseEntity<String> deleteBook(@PathVariable String username, @RequestParam String isbn){
        try{
            this.bookshelfService.deleteBook(username, isbn);
            return ResponseEntity.ok().build();
        } catch (CustomerNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }

    @PreAuthorize("#username == principal.username")
    @GetMapping("/exclusivityClass")
    public ResponseEntity<?> getExclusivityClasses(@PathVariable String username, @RequestParam String name){
        try{
            Set<ExclusivityClassDTO> exclusivityClassDTOS = this.bookshelfService.getExclusivityClasses(username);
            return ResponseEntity.ok().body(exclusivityClassDTOS);
        } catch (CustomerNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }

    @PreAuthorize("#username == principal.username")
    @PostMapping("/exclusivityClass")
    public ResponseEntity<String> createExclusivityClass(@PathVariable String username, @RequestParam String name){
        try{
            this.bookshelfService.createExclusivityClass(username, name);
            return ResponseEntity.ok().build();
        } catch (CustomerNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        } catch (ExclusivityClassAlreadyExistsException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

    @PreAuthorize("#username == principal.username")
    @PutMapping("/exclusivityClass/{name}")
    public ResponseEntity<String> changeExclusivityClassName(@PathVariable String username, @PathVariable String name, @RequestParam String newName){
        try{
            this.bookshelfService.changeExclusivityClassName(username, name, newName);
            return ResponseEntity.ok().build();
        } catch (CustomerNotFoundException | ExclusivityClassNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        } catch (ExclusivityClassAlreadyExistsException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

    @PreAuthorize("#username == principal.username")
    @DeleteMapping("/exclusivityClass")
    public ResponseEntity<String> deleteExclusivityClass(@PathVariable String username, @RequestParam String name){
        try{
            this.bookshelfService.deleteExclusivityClass(username, name);
            return ResponseEntity.ok().build();
        } catch (CustomerNotFoundException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage());
        }
    }
}

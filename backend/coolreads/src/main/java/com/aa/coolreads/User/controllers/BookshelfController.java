package com.aa.coolreads.User.controllers;

import com.aa.coolreads.User.dto.BookShelfDTO;
import com.aa.coolreads.User.services.BookshelfService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/customer/{username}/bookshelf")
public class BookshelfController {

    private final BookshelfService bookshelfService;

    @Autowired
    public BookshelfController(BookshelfService bookshelfService) {
        this.bookshelfService = bookshelfService;
    }

    @PostMapping
    public void insertBookshelf(@PathVariable String username, @RequestBody BookShelfDTO bookshelf){

    }
}

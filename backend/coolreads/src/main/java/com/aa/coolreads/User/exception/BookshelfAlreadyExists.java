package com.aa.coolreads.User.exception;

public class BookshelfAlreadyExists extends Exception {
    public BookshelfAlreadyExists(String name){
        super("Bookshelf already exists!: " + name);
    }
}

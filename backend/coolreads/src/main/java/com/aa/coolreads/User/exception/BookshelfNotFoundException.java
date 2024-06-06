package com.aa.coolreads.User.exception;

public class BookshelfNotFoundException extends Exception{
    public BookshelfNotFoundException(String name) {
        super("Bookshelf with name '" + name + "' not found!");
    }
}

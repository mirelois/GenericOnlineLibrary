package com.aa.coolreads.Book.exception;

public class BookNotFoundException extends Exception{
    public BookNotFoundException(String isbn) {
        super("book with isbn '" + isbn + "' not found!");
    }
}

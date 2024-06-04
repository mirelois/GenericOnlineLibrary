package com.aa.coolreads.Book.exception;

public class InvalidISBNExeption extends Exception {
    public InvalidISBNExeption(String isbn) {
        super("The ISBN Number:" + isbn + "is not valid");
    }
}

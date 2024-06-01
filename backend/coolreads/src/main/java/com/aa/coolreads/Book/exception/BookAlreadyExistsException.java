package com.aa.coolreads.Book.exception;

public class BookAlreadyExistsException extends Exception{
    public BookAlreadyExistsException(String isbn){
        super("book with isbn '" + isbn + "' already exists!");
    }
}

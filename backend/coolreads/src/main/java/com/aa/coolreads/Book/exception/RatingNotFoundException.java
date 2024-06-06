package com.aa.coolreads.Book.exception;

public class RatingNotFoundException extends Exception{
    public RatingNotFoundException(String isbn, String username) {
        super("rating from book with isbn '" + isbn + "' and Customer with username '" + username + "' not found!");
    }
}

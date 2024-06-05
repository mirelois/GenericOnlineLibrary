package com.aa.coolreads.Book.exception;

public class ReviewNotFoundException extends Exception{
    public ReviewNotFoundException(String isbn, String username){
        super("Review from book with isbn '" + isbn + "' and Customer with username '" + username + "' not found!");
    }
}

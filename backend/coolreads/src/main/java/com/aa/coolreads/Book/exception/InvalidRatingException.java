package com.aa.coolreads.Book.exception;

public class InvalidRatingException extends Exception{
    public InvalidRatingException(Double rating) {
        super(String.format("Invalid rating: %f", rating));
    }
}

package com.aa.coolreads.Book.exception;

public class InvalidRatingExeption extends Exception{
    public InvalidRatingExeption(Double rating) {
        super(String.format("Invalid rating: %f", rating));
    }
}

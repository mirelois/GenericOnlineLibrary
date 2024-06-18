package com.aa.coolreads.User.exception;

public class InvalidPageReadException extends Exception{
    public InvalidPageReadException(Integer pagesRead){
        super(pagesRead < 0 ? "Page read must be above or equal to 0" : "page read number is higher than books total page count!");
    }
}

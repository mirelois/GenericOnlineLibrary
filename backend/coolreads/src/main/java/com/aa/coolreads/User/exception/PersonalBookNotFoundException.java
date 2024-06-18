package com.aa.coolreads.User.exception;

public class PersonalBookNotFoundException extends Exception{
    public PersonalBookNotFoundException(String isbn){
        super("The book with isbn was not found: " + isbn);
    }
}

package com.aa.coolreads.User.exception;

public class PersonalBookAlreadyExistsException extends Exception{
    public PersonalBookAlreadyExistsException(String isbn){
        super("The book with isbn " + isbn + " is already added to that bookshelf");
    }
}

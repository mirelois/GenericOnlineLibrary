package com.aa.coolreads.User.exception;

public class PersonalBookAlreadyExistsException extends Exception{
    public PersonalBookAlreadyExistsException(String isbn){
        super("The book is already added to bookshelf: " + isbn);
    }
}

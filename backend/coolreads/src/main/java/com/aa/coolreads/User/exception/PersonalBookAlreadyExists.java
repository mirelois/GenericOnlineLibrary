package com.aa.coolreads.User.exception;

public class PersonalBookAlreadyExists extends Exception{
    public PersonalBookAlreadyExists(String isbn){
        super("The book is already added to bookshelf: " + isbn);
    }
}

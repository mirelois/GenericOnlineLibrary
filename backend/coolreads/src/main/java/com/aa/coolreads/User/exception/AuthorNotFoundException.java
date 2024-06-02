package com.aa.coolreads.User.exception;

public class AuthorNotFoundException extends Exception{
    public AuthorNotFoundException(String userName){
        super("author with username '" + userName + "' not found!");
    }
}

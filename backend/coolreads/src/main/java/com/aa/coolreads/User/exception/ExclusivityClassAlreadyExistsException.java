package com.aa.coolreads.User.exception;

public class ExclusivityClassAlreadyExistsException extends Exception {
    public ExclusivityClassAlreadyExistsException(String name){
        super("The exclusivity class with name already exists: " + name);
    }
}

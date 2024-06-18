package com.aa.coolreads.User.exception;

public class ExclusivityClassNotFoundException extends Exception{
    public ExclusivityClassNotFoundException(String name){
        super("The exclusivity class with name " + name + " was not found!");
    }
}

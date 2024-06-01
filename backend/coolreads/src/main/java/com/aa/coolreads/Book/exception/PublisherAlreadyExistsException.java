package com.aa.coolreads.Book.exception;

public class PublisherAlreadyExistsException extends Exception{
    public PublisherAlreadyExistsException(String name){
        super("publisher with name '" + name + "' already exists!");
    }
}

package com.aa.coolreads.Book.exception;

public class PublisherNotFoundException extends Exception{
    public PublisherNotFoundException(String name){
            super("publisher with name '" + name + "' not found!");
        }
}

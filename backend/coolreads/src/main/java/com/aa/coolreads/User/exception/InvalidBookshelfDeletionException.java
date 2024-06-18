package com.aa.coolreads.User.exception;

public class InvalidBookshelfDeletionException extends Exception{
    public InvalidBookshelfDeletionException(String name){
        super("The bookshelf trying to be deleted is default and therefore cannot be deleted: " + name);
    }
}

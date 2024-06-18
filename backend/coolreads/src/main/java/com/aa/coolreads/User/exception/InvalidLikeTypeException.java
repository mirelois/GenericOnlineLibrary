package com.aa.coolreads.User.exception;

public class InvalidLikeTypeException extends Exception {
    public InvalidLikeTypeException(String likeType){
        super("The likeType is invalid: " + likeType);
    }
}

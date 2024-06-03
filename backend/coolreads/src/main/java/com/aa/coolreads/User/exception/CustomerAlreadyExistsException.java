package com.aa.coolreads.User.exception;

public class CustomerAlreadyExistsException extends Exception{
    public CustomerAlreadyExistsException(String userName){
        super("customer with username '" + userName + "' already exists!");
    }
}

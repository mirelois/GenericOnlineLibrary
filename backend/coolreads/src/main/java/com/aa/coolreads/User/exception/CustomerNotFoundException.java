package com.aa.coolreads.User.exception;

public class CustomerNotFoundException extends Exception{
    public CustomerNotFoundException(String userName){
        super("customer with username '" + userName + "' not found!");
    }
}

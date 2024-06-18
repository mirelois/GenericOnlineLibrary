package com.aa.coolreads.User.exception;

public class PasswordsDontMatchException extends Exception{
    public PasswordsDontMatchException(){
        super("The passwords don't match");
    }
}

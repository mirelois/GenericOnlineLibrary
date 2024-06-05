package com.aa.coolreads.Book.exception;

import java.util.Date;

public class InsufficientReviewParametersException extends Exception{
    public InsufficientReviewParametersException(String description, Date date){
        super((description == null ? "Parameter description is null!" : "") + (date == null ? "Parameter date is null!" : ""));
    }
}

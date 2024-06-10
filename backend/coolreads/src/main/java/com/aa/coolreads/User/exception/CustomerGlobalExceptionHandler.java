package com.aa.coolreads.User.exception;

import org.springframework.http.HttpStatusCode;
import org.springframework.http.ProblemDetail;
import org.springframework.security.authentication.AccountStatusException;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.nio.file.AccessDeniedException;
import java.security.SignatureException;

@RestControllerAdvice
public class CustomerGlobalExceptionHandler {
    public ProblemDetail handleSecurityException(BadCredentialsException badCredentialsException){
        ProblemDetail problemDetail = ProblemDetail.forStatusAndDetail(HttpStatusCode.valueOf(401), badCredentialsException.getMessage());
        problemDetail.setProperty("description", "Invalid Credentials");

        return problemDetail;
    }

    public ProblemDetail handleSecurityException(AccountStatusException accountStatusException){
        ProblemDetail problemDetail = ProblemDetail.forStatusAndDetail(HttpStatusCode.valueOf(403), accountStatusException.getMessage());
        problemDetail.setProperty("description", "The account is locked");

        return problemDetail;
    }

    public ProblemDetail handleSecurityException(AccessDeniedException accessDeniedException){
        ProblemDetail problemDetail = ProblemDetail.forStatusAndDetail(HttpStatusCode.valueOf(403), accessDeniedException.getMessage());
        problemDetail.setProperty("description", "You are not authorized to access this resource");

        return problemDetail;
    }

    public ProblemDetail handleSecurityException(SignatureException signatureException){
        ProblemDetail problemDetail = ProblemDetail.forStatusAndDetail(HttpStatusCode.valueOf(403), signatureException.getMessage());
        problemDetail.setProperty("description", "The JWT signature is invalid");

        return problemDetail;
    }
}

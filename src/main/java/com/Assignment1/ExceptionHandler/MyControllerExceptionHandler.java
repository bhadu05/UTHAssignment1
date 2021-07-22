package com.Assignment1.ExceptionHandler;

import io.jsonwebtoken.ExpiredJwtException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.InternalAuthenticationServiceException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.security.SignatureException;
import java.sql.SQLIntegrityConstraintViolationException;

@ControllerAdvice
public class MyControllerExceptionHandler {


    @ExceptionHandler(CustomException.class)
    public ResponseEntity<ExceptionMessage> handleInvalidArgument(CustomException invalidArgumentException)
    {
        return new ResponseEntity<ExceptionMessage>(new ExceptionMessage("Invalid Arguments",invalidArgumentException.getErrorMessage(),HttpStatus.BAD_REQUEST.value()), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(ExpiredJwtException.class)
    public ResponseEntity<String> tokenExpired(ExpiredJwtException expiredJwtException) {
        expiredJwtException.getMessage();
        return new ResponseEntity<String>(expiredJwtException.getMessage(), HttpStatus.FORBIDDEN);
    }

    @ExceptionHandler(InternalAuthenticationServiceException.class)
    public ResponseEntity<String >unauthorisedUser(InternalAuthenticationServiceException internalAuthenticationServiceException)
    {
        return new ResponseEntity<String>("Invalid username or password.", HttpStatus.UNAUTHORIZED);
    }

    @ExceptionHandler(SignatureException.class)
    public ResponseEntity<String> unauthorisedToken(SignatureException signatureException)
    {
        return new ResponseEntity<String >(signatureException.getMessage(),HttpStatus.UNAUTHORIZED);
    }

    @ExceptionHandler(SQLIntegrityConstraintViolationException.class)
    public ResponseEntity<String> handleDulplicates(SQLIntegrityConstraintViolationException invalidArgumentException)
    {
        invalidArgumentException.getMessage();
        return new ResponseEntity<String>(invalidArgumentException.getMessage(), HttpStatus.BAD_REQUEST);
    }
}

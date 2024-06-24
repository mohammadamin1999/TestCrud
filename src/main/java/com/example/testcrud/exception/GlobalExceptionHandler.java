package com.example.testcrud.exception;


import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import static org.springframework.http.HttpStatus.*;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(Exception.class)
    public ResponseEntity<String> handleAllException(Exception e){
        return new ResponseEntity<>(e.getMessage() , INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(DataIntegrityViolationException.class)
    public ResponseEntity<String> dataIntegrityViolationException(DataIntegrityViolationException e){
        String message = e.getMessage();
        if (message.contains("PHONE_NUMBER")) {
            return new ResponseEntity<>("phone number already exist in data base!" , CONFLICT);
        } else if (message.contains("USERNAME")) {
            return new ResponseEntity<>("username already exist in data base!" , CONFLICT);
        }
        return new ResponseEntity<>(e.getMessage() , CONFLICT);
    }


}

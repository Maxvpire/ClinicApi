package com.maxvpire.administration.handler;

import com.maxvpire.administration.exception.AdministrationNotFoundException;
import com.maxvpire.administration.exception.NotValidGenderTypeException;
import com.maxvpire.administration.exception.RepeatedActionException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;

@RestControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(AdministrationNotFoundException.class)
    public ResponseEntity<String> administrationNotFound(AdministrationNotFoundException e) {
        return ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .body(e.getMessage());
    }

    @ExceptionHandler(NotValidGenderTypeException.class)
    public ResponseEntity<String> notValidGenderType(NotValidGenderTypeException e) {
        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(e.getMessage());
    }

    @ExceptionHandler(RepeatedActionException.class)
    public ResponseEntity<String> repeatedActionException(RepeatedActionException e) {
        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(e.getMessage());
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorResponse> methodArgumentNotValid(MethodArgumentNotValidException e) {
        HashMap<String, String> errors = new HashMap<String, String>();
        e.getBindingResult().getAllErrors().forEach((err) -> {
            String fieldName = ((FieldError) err).getField();
            String errMsg = err.getDefaultMessage();
            errors.put(fieldName, errMsg);
        });
        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(new ErrorResponse(errors));
    }
}

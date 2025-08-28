package com.maxvpire.medicalrecords.handler;

import com.maxvpire.medicalrecords.exception.MedicalRecordNotFoundException;
import com.maxvpire.medicalrecords.exception.PrescriptionNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;

@RestControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(MedicalRecordNotFoundException.class)
    public ResponseEntity<String> handleException(MedicalRecordNotFoundException ex){
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex.getMessage());
    }

    @ExceptionHandler
    public ResponseEntity<String> handleException(PrescriptionNotFoundException ex){
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex.getMessage());
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorResponse> handle(MethodArgumentNotValidException exp) {
        HashMap<String, String> errors = new HashMap<String, String>();
        exp.getBindingResult().getAllErrors().forEach((err) -> {
            String fieldName = ((FieldError) err).getField();
            String errMsg = err.getDefaultMessage();
            errors.put(fieldName, errMsg);
        });
        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(new ErrorResponse(errors));
    }
}

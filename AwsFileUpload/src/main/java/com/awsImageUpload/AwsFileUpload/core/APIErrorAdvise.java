package com.ft.ltc.core;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
 
@ControllerAdvice 
public class APIErrorAdvise  {

    @ExceptionHandler(Exception.class)
    public ResponseEntity<APIError> handleGenericException(Exception ex) {
        System.out.println("FFF");
        APIError apiError =  new APIError(HttpStatus.INTERNAL_SERVER_ERROR, ex.getMessage(), "some fancy description") ;
        return new ResponseEntity<>(apiError, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
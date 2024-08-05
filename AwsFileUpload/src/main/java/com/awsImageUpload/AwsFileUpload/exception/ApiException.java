package com.ft.ltc.exception;

import org.springframework.stereotype.Component;

@Component
public class ApiException extends Exception {

    private String errorCode;

    private String errorMessage;

    public ApiException(String errorCode, String errorMessage){
        super();
        this.errorCode= errorCode;
        this.errorMessage= errorMessage;
    }

    public ApiException(){
        super();
    }
}

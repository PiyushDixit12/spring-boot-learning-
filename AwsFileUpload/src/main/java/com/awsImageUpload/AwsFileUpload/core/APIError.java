package com.ft.ltc.core;

import java.util.Arrays;
import java.util.List;

//import lombok.Data;
import org.springframework.http.HttpStatus;

//@Data
public class APIError {
 
    private boolean success;
    private String data;
    private HttpStatus status;
    private String message;
    private List<String> errors;

    public APIError(HttpStatus status, String message, List<String> errors) {
        super();
        this.success = false;
        this.status = status;
        this.message = message;
        this.errors = errors;
    }

    public APIError(HttpStatus status, String message, String error) {
        super();
        this.success = false;
        this.status = status;
        this.message = message;
        errors = Arrays.asList(error);
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public HttpStatus getStatus() {
        return status;
    }

    public void setStatus(HttpStatus status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public List<String> getErrors() {
        return errors;
    }

    public void setErrors(List<String> errors) {
        this.errors = errors;
    }
}
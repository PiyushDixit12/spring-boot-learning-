package com.example.blog_app_api.exceptions;

import lombok.Data;

@Data
public class ResourceNotFoundException extends RuntimeException{
    private  String resourceName;
    private  String fieldName;
    private  int fieldValue;
    public ResourceNotFoundException(String resourceName, String fieldName, int fieldValue) {
        super(String.format("Resource %s not found with name %s and value %d", resourceName, fieldName, fieldValue));
        this.resourceName = resourceName;
        this.fieldName = fieldName;
        this.fieldValue = fieldValue;
    }


}

package com.ft.ltc.core;

import com.ft.ltc.dto.ApiResponse;
import org.springframework.core.MethodParameter;
import org.springframework.http.MediaType;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.lang.Nullable;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice;

@ControllerAdvice
public class ApiResponseAdvice implements ResponseBodyAdvice<Object> {

    @Override
    public boolean supports(MethodParameter returnType, Class converterType) {
        // Apply this advice to all @RestController methods
        return true;
    }

    @Override
    @Nullable
    public Object beforeBodyWrite(Object body, MethodParameter returnType, MediaType selectedContentType, Class selectedConverterType, ServerHttpRequest request, ServerHttpResponse response) {
        if (body instanceof ApiResponse)   {
            return body; // Avoid wrapping already wrapped responses
        }
        ApiResponse apiResponse = new ApiResponse();

        if (body instanceof APIError ) {
            apiResponse.setError(((APIError)body).getMessage());
            apiResponse.setSuccess(false);
        }
        else{
            apiResponse.setData(body);
            apiResponse.setSuccess(true);
        }
        //TODO: Fix this. AT
        apiResponse.setProcessingTime((Long)(System.currentTimeMillis() - 0)) ;// (Long) httpServletRequest.getAttribute("startTime") ));

        return apiResponse;
    }
 
}

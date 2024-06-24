package com.example.testcrud.service;


import com.example.testcrud.model.dto.BaseResponse;
import org.springframework.http.HttpStatus;


public abstract class BaseService {

    @SuppressWarnings("unchecked")
    protected <T> BaseResponse<T> buildResponseOk(HttpStatus status, T data) {
        return (BaseResponse<T>) BaseResponse.builder()
                .statusCode(status.value())
                .data(data).build();
    }

    @SuppressWarnings("unchecked")
    protected <T> BaseResponse<T> buildResponseError(HttpStatus status, String errorMassage) {
        return (BaseResponse<T>) BaseResponse.builder()
                .statusCode(status.value())
                .errorMassage(errorMassage).build();
    }
}

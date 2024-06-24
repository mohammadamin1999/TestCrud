package com.example.testcrud.controller;

import com.example.testcrud.model.dto.BaseResponse;
import com.example.testcrud.service.BaseService;
import org.springframework.http.ResponseEntity;

public abstract class BaseController {

    protected <T> ResponseEntity<T> buildResponse(BaseResponse<T> response) {
        return ResponseEntity.status(response.getStatusCode()).body(response.getData());
    }
}

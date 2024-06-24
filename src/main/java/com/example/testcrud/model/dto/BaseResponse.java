package com.example.testcrud.model.dto;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Builder
public class BaseResponse<T> {
    private String SuccessMassage;
    private String errorMassage;
    private Integer statusCode;
    private T data;
}

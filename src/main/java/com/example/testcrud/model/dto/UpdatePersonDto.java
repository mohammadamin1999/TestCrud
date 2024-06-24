package com.example.testcrud.model.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class UpdatePersonDto {
    private Long id;
    private String name;
    private String username;
    private String phoneNumber;
}

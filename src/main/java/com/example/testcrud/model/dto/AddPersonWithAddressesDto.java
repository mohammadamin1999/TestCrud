package com.example.testcrud.model.dto;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;


@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class AddPersonWithAddressesDto {
    private String name;
    private String username;
    private String phoneNumber;
    private List<String> addresses;
}

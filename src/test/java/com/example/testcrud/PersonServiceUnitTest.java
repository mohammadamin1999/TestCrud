package com.example.testcrud;

import com.example.testcrud.model.dto.AddPersonWithAddressesDto;
import com.example.testcrud.model.dto.BaseResponse;
import com.example.testcrud.service.PersonService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
public class PersonServiceUnitTest {

    @Autowired
    private PersonService service;

    @Test
    public void addPersonWithAddressTest(){
        assertThat(this.service).isNotNull();
        AddPersonWithAddressesDto dto = AddPersonWithAddressesDto.builder()
                .addresses(new ArrayList<>())
                .username("asasasas")
                .name("ali")
                .phoneNumber("0255415478")
                .build();
        BaseResponse<?> response = service.addPersonWithAddresses(dto);
        assertThat(response.getStatusCode()).isEqualTo(200);
        assertThat(response.getData()).isNotNull();
    }
}

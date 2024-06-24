package com.example.testcrud.service;


import com.example.testcrud.model.dto.AddPersonWithAddressesDto;
import com.example.testcrud.model.dto.BaseResponse;
import com.example.testcrud.model.dto.PersonDto;
import com.example.testcrud.model.dto.UpdatePersonDto;
import org.springframework.http.ResponseEntity;

public interface PersonService {
    BaseResponse<?> addPerson(PersonDto person);
    BaseResponse<?> getPerson(Long id);
    BaseResponse<?> updatePerson(UpdatePersonDto person);
    BaseResponse<?> deletePerson(Long id);

    BaseResponse<?> addPersonWithAddresses(AddPersonWithAddressesDto personDto);
}

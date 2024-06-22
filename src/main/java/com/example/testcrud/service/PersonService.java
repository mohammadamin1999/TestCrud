package com.example.testcrud.service;


import com.example.testcrud.controller.dto.AddPersonWithAddressesDto;
import com.example.testcrud.controller.dto.PersonDto;
import com.example.testcrud.controller.dto.UpdatePersonDto;
import org.springframework.http.ResponseEntity;

public interface PersonService {
    ResponseEntity<?> addPerson(PersonDto person);
    ResponseEntity<?> getPerson(Long id);
    ResponseEntity<?> updatePerson(UpdatePersonDto person);
    ResponseEntity<?> deletePerson(Long id);

    ResponseEntity<?> addPersonWithAddresses(AddPersonWithAddressesDto personDto);
}

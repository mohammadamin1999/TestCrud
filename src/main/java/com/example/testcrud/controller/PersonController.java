package com.example.testcrud.controller;

import com.example.testcrud.model.dto.AddPersonWithAddressesDto;
import com.example.testcrud.model.dto.PersonDto;
import com.example.testcrud.model.dto.UpdatePersonDto;
import com.example.testcrud.service.PersonService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/person")
@RequiredArgsConstructor
public class PersonController extends BaseController {
    private final PersonService service;


    @PostMapping("")
    public ResponseEntity<?> addPerson(@RequestBody PersonDto dto){
        return buildResponse(service.addPerson(dto));
    }

    @PutMapping("")
    public ResponseEntity<?> updatePerson(@RequestBody UpdatePersonDto dto){
        return buildResponse(service.updatePerson(dto));
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getPersonById(@PathVariable(value = "id") Long id){
        return buildResponse(service.getPerson(id));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deletePerson(@PathVariable(value = "id") Long id){
        return buildResponse(service.deletePerson(id));
    }

    @PostMapping("/withAddress")
    public ResponseEntity<?> addPersonWithAddresses(@RequestBody AddPersonWithAddressesDto dto){
        return buildResponse(service.addPersonWithAddresses(dto));
    }
}

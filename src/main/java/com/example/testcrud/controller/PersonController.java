package com.example.testcrud.controller;

import com.example.testcrud.controller.dto.AddPersonWithAddressesDto;
import com.example.testcrud.controller.dto.PersonDto;
import com.example.testcrud.controller.dto.UpdatePersonDto;
import com.example.testcrud.service.PersonService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/person")
@RequiredArgsConstructor
public class PersonController {
    private final PersonService service;


    @PostMapping("")
    public ResponseEntity<?> addPerson(@RequestBody PersonDto dto){
        return service.addPerson(dto);
    }

    @PutMapping("")
    public ResponseEntity<?> updatePerson(@RequestBody UpdatePersonDto dto){
        return service.updatePerson(dto);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getPersonById(@PathVariable(value = "id") Long id){
        return service.getPerson(id);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deletePerson(@PathVariable(value = "id") Long id){
        return service.deletePerson(id);
    }

    @PostMapping("/withAddress")
    public ResponseEntity<?> addPersonWithAddresses(@RequestBody AddPersonWithAddressesDto dto){
        return service.addPersonWithAddresses(dto);
    }
}

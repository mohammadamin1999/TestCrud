package com.example.testcrud.controller;

import com.example.testcrud.controller.dto.*;
import com.example.testcrud.service.AddressService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequiredArgsConstructor
@RequestMapping("/address")
public class AddressController {

    private final AddressService service;


    @PostMapping("")
    public ResponseEntity<?> addAddress(@RequestBody AddressDto dto){
        return service.addAddress(dto);
    }

    @PutMapping("")
    public ResponseEntity<?> updateAddress(@RequestBody GetAddressDto dto){
        return service.updateAddress(dto);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getAddressById(@PathVariable(value = "id") Long id){
        return service.getAddress(id);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteAddress(@PathVariable(value = "id") Long id){
        return service.deleteAddress(id);
    }
}

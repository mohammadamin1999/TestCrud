package com.example.testcrud.controller;

import com.example.testcrud.model.dto.AddressDto;
import com.example.testcrud.model.dto.GetAddressDto;
import com.example.testcrud.service.AddressService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequiredArgsConstructor
@RequestMapping("/address")
public class AddressController extends BaseController {

    private final AddressService service;


    @PostMapping("")
    public ResponseEntity<?> addAddress(@RequestBody AddressDto dto){
        return buildResponse(service.addAddress(dto));
    }

    @PutMapping("")
    public ResponseEntity<?> updateAddress(@RequestBody GetAddressDto dto){
        return buildResponse(service.updateAddress(dto));
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getAddressById(@PathVariable(value = "id") Long id){
        return buildResponse(service.getAddress(id));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteAddress(@PathVariable(value = "id") Long id){
        return buildResponse(service.deleteAddress(id));
    }
}

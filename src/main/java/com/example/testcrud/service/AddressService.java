package com.example.testcrud.service;

import com.example.testcrud.controller.dto.AddressDto;
import com.example.testcrud.controller.dto.GetAddressDto;
import com.example.testcrud.controller.dto.UpdateAddressDto;
import org.springframework.http.ResponseEntity;

public interface AddressService {
    ResponseEntity<?> addAddress(AddressDto address);
    ResponseEntity<?> updateAddress(GetAddressDto address);
    ResponseEntity<?> getAddress(Long id);
    ResponseEntity<?> deleteAddress(Long id);
}

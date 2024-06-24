package com.example.testcrud.service;

import com.example.testcrud.model.dto.AddressDto;
import com.example.testcrud.model.dto.BaseResponse;
import com.example.testcrud.model.dto.GetAddressDto;

public interface AddressService {
    BaseResponse<?> addAddress(AddressDto address);
    BaseResponse<?> updateAddress(GetAddressDto address);
    BaseResponse<?> getAddress(Long id);
    BaseResponse<?> deleteAddress(Long id);
}

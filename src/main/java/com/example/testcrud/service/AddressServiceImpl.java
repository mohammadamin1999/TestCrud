package com.example.testcrud.service;

import com.example.testcrud.model.dto.AddressDto;
import com.example.testcrud.model.dto.BaseResponse;
import com.example.testcrud.model.dto.GetAddressDto;
import com.example.testcrud.model.entity.Address;
import com.example.testcrud.model.entity.Person;
import com.example.testcrud.repository.AddressRepository;
import com.example.testcrud.repository.PersonRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.time.Instant;
import java.util.Optional;

import static org.springframework.http.HttpStatus.*;

@Service
@RequiredArgsConstructor
public class AddressServiceImpl extends BaseService implements AddressService {

    private final AddressRepository repository;
    private final PersonRepository personRepository;

    @Override
    public BaseResponse<?> addAddress(AddressDto address) {
        if (!address.getText().isEmpty()) {
            Optional<Person> optionalPerson = personRepository.findPersonByIdAndDeleteDate(address.getPersonId() , 0L);
            if (optionalPerson.isPresent()) {
                Person person = optionalPerson.get();
                Address newAddress = Address.builder()
                        .text(address.getText())
                        .person(person)
                        .build();
                Address save = repository.save(newAddress);
                return buildResponseOk(OK , save.getId());
            }
            return buildResponseOk(NOT_FOUND , "Person Not Found!");
        }
        return buildResponseOk(BAD_REQUEST , "Text Can Not Be Empty!");
    }

    @Override
    public BaseResponse<?> updateAddress(GetAddressDto address) {
        if (!address.getText().isEmpty()) {
            Optional<Address> optionalAddress = repository.findAddressByIdAndDeleteDate(address.getId(),  0L);
            if (optionalAddress.isPresent()) {
                Address foundAddress = optionalAddress.get();
                foundAddress.setText(address.getText());
                Address save = repository.save(foundAddress);
                return buildResponseOk(OK , save.getId());
            }
            return buildResponseOk(NOT_FOUND , "Address Not Found!");
        }
        return buildResponseOk(BAD_REQUEST , "Text Can Not Be Empty!");
    }

    @Override
    public BaseResponse<?> getAddress(Long id) {
        Optional<Address> addressOptional = repository.findAddressByIdAndDeleteDate(id , 0L);
        if (addressOptional.isPresent()) {
            Address address = addressOptional.get();
            AddressDto dto = AddressDto.builder()
                    .personId(address.getPerson().getId())
                    .text(address.getText())
                    .build();
            return buildResponseOk(OK , dto);
        }
        return buildResponseError(NOT_FOUND , "Address Not Found!");
    }

    @Override
    public BaseResponse<?> deleteAddress(Long id) {
        Optional<Address> addressOptional = repository.findAddressByIdAndDeleteDate(id, 0L);
        if (addressOptional.isPresent()) {
            Address address = addressOptional.get();
            address.setDeleteDate(Date.from(Instant.now()).getTime());
            repository.save(address);
            return buildResponseOk(OK , "Done");
        }
        return buildResponseError(NOT_FOUND , "Address Not Found!");
    }
}

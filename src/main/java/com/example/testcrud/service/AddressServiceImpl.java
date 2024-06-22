package com.example.testcrud.service;

import com.example.testcrud.controller.dto.AddressDto;
import com.example.testcrud.controller.dto.GetAddressDto;
import com.example.testcrud.controller.dto.UpdateAddressDto;
import com.example.testcrud.model.Address;
import com.example.testcrud.model.Person;
import com.example.testcrud.repository.AddressRepository;
import com.example.testcrud.repository.PersonRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.time.Instant;
import java.util.List;
import java.util.Optional;

import static org.springframework.http.HttpStatus.*;

@Service
@RequiredArgsConstructor
public class AddressServiceImpl implements AddressService {

    private final AddressRepository repository;
    private final PersonRepository personRepository;

    @Override
    public ResponseEntity<?> addAddress(AddressDto address) {
        if (!address.getText().isEmpty()) {
            Optional<Person> optionalPerson = personRepository.findPersonByIdAndDeleteDate(address.getPersonId() , 0L);
            if (optionalPerson.isPresent()) {
                Person person = optionalPerson.get();
                Address newAddress = Address.builder()
                        .text(address.getText())
                        .build();
                List<Address> addressList = person.getAddressList();
                addressList.add(newAddress);
                person.setAddressList(addressList);
                Address save = repository.save(newAddress);
                Person updatedPerson = personRepository.save(person);
                return ResponseEntity.ok(save.getId());
            }
            return ResponseEntity.status(NOT_FOUND).body("person not found");
        }
        return ResponseEntity.status(BAD_REQUEST).body("text can not be empty");
    }

    @Override
    public ResponseEntity<?> updateAddress(GetAddressDto address) {
        if (!address.getText().isEmpty()) {
            Optional<Address> optionalAddress = repository.findAddressByIdAndDeleteDate(address.getId(),  0L);
            if (optionalAddress.isPresent()) {
                Address foundAddress = optionalAddress.get();
                foundAddress.setText(address.getText());
                Address save = repository.save(foundAddress);
                return ResponseEntity.ok(save.getId());
            }
            return ResponseEntity.status(NOT_FOUND).body("address not found");
        }
        return ResponseEntity.status(BAD_REQUEST).body("text can not be empty");
    }

    @Override
    public ResponseEntity<?> getAddress(Long id) {
        Optional<Address> dtoOptional = repository.findAddressByIdAndDeleteDate(id , 0L);
        if (dtoOptional.isPresent()) {
            Address address = dtoOptional.get();
            return ResponseEntity.ok(address);
        }
        return ResponseEntity.status(NOT_FOUND).body("address not found");
    }

    @Override
    public ResponseEntity<?> deleteAddress(Long id) {
        Optional<Address> addressOptional = repository.findAddressByIdAndDeleteDate(id, 0L);
        if (addressOptional.isPresent()) {
            Address address = addressOptional.get();
            address.setDeleteDate(Date.from(Instant.now()).getTime());
            Address save = repository.save(address);
            return ResponseEntity.ok("Done");
        }
        return ResponseEntity.status(NOT_FOUND).body("address not found");
    }
}

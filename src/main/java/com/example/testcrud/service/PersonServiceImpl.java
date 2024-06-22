package com.example.testcrud.service;

import com.example.testcrud.controller.dto.AddPersonWithAddressesDto;
import com.example.testcrud.controller.dto.GetAddressDto;
import com.example.testcrud.controller.dto.PersonDto;
import com.example.testcrud.controller.dto.UpdatePersonDto;
import com.example.testcrud.model.Address;
import com.example.testcrud.model.Person;
import com.example.testcrud.repository.AddressRepository;
import com.example.testcrud.repository.PersonRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.springframework.http.HttpStatus.NOT_FOUND;

@Service
@RequiredArgsConstructor
public class PersonServiceImpl implements PersonService {

    private final PersonRepository repository;
    private final AddressRepository addressRepository;

    @Override
    public ResponseEntity<?> addPerson(PersonDto person) {
        Person newPerson = Person.builder()
                .name(person.getName())
                .phoneNumber(person.getPhoneNumber())
                .username(person.getUsername())
                .build();
        Person save = repository.save(newPerson);
        return ResponseEntity.ok(save.getId());
    }

    @Override
    public ResponseEntity<?> getPerson(Long id) {
        Optional<Person> optionalPerson = repository.findPersonById(id);
        if (optionalPerson.isPresent()) {
            Person person = optionalPerson.get();
            if (person.getDeleteDate() == 0L) {
                List<Address> addressList = person.getAddressList();
                List<GetAddressDto> addressDtoList = new ArrayList<>();
                for (Address a :
                        addressList) {
                    GetAddressDto dto = GetAddressDto.builder()
                            .text(a.getText())
                            .id(a.getId())
                            .build();
                    addressDtoList.add(dto);
                }
                PersonDto dto = PersonDto.builder()
                        .name(person.getName())
                        .phoneNumber(person.getPhoneNumber())
                        .username(person.getUsername())
                        .addressDtoList(addressDtoList)
                        .build();
                return ResponseEntity.ok(dto);
            }
        }
        return ResponseEntity.status(NOT_FOUND).body("person not found");
    }

    @Override
    public ResponseEntity<?> updatePerson(UpdatePersonDto person) {
        Optional<Person> optionalPerson = repository.findPersonById(person.getId());
        if (optionalPerson.isPresent()) {
            Person foundPerson = optionalPerson.get();
            if (foundPerson.getDeleteDate() == 0L) {
                foundPerson.setName(person.getName());
                foundPerson.setUsername(person.getUsername());
                foundPerson.setPhoneNumber(person.getPhoneNumber());
                Person save = repository.save(foundPerson);
                return ResponseEntity.ok(save.getId());
            }
        }
        return ResponseEntity.status(NOT_FOUND).body("person not found");
    }

    @Override
    public ResponseEntity<?> deletePerson(Long id) {
        Optional<Person> optionalPerson = repository.findPersonById(id);
        if (optionalPerson.isPresent()) {
            Person foundPerson = optionalPerson.get();
            if (foundPerson.getDeleteDate() == 0L) {
                foundPerson.setDeleteDate(Date.from(Instant.now()).getTime());
                List<Address> addressList = foundPerson.getAddressList();
                for (Address a :
                        addressList) {
                    a.setDeleteDate(Date.from(Instant.now()).getTime());
                    addressRepository.save(a);
                }
                Person save = repository.save(foundPerson);
                return ResponseEntity.ok(save.getId());
            }
        }
        return ResponseEntity.status(NOT_FOUND).body("person not found");
    }

    @Override
    public ResponseEntity<?> addPersonWithAddresses(AddPersonWithAddressesDto personDto) {
        List<Address> addressList = new ArrayList<>();
        List<String> addressesText = personDto.getAddresses();
        for (String s :
                addressesText) {
            Address address = Address.builder()
                    .text(s)
                    .build();
            addressRepository.save(address);
            addressList.add(address);
        }
        Person person = Person.builder()
                .username(personDto.getUsername())
                .phoneNumber(personDto.getPhoneNumber())
                .addressList(addressList)
                .name(personDto.getName())
                .build();
        Person save = repository.save(person);
        return ResponseEntity.ok(save.getId());
    }
}

package com.example.testcrud.service;

import com.example.testcrud.model.entity.Address;
import com.example.testcrud.model.entity.Person;
import com.example.testcrud.model.dto.*;
import com.example.testcrud.repository.AddressRepository;
import com.example.testcrud.repository.PersonRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.springframework.http.HttpStatus.NOT_FOUND;
import static org.springframework.http.HttpStatus.OK;

@Service
@RequiredArgsConstructor
public class PersonServiceImpl extends BaseService implements PersonService {

    private final PersonRepository repository;
    private final AddressRepository addressRepository;

    @Override
    public BaseResponse<?> addPerson(PersonDto person) {
        Person newPerson = Person.builder()
                .name(person.getName())
                .phoneNumber(person.getPhoneNumber())
                .username(person.getUsername())
                .build();
        Person save = repository.save(newPerson);

        return buildResponseOk(OK , save.getId());
    }

    @Override
    public BaseResponse<?> getPerson(Long id) {
        Optional<Person> optionalPerson = repository.findPersonByIdAndDeleteDate(id , 0L);
        if (optionalPerson.isPresent()) {
            Person person = optionalPerson.get();
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
            return buildResponseOk(OK , dto);
        }
        return buildResponseOk(NOT_FOUND , "Person Not Found!");
    }

    @Override
    public BaseResponse<?> updatePerson(UpdatePersonDto person) {
        Optional<Person> optionalPerson = repository.findPersonById(person.getId());
        if (optionalPerson.isPresent()) {
            Person foundPerson = optionalPerson.get();
            if (foundPerson.getDeleteDate() == 0L) {
                foundPerson.setName(person.getName());
                foundPerson.setUsername(person.getUsername());
                foundPerson.setPhoneNumber(person.getPhoneNumber());
                Person save = repository.save(foundPerson);
                return buildResponseOk(OK , save.getId());
            }
        }
        return buildResponseOk(NOT_FOUND , "Person Not Found!");
    }

    @Override
    public BaseResponse<?> deletePerson(Long id) {
        Optional<Person> optionalPerson = repository.findPersonByIdAndDeleteDate(id , 0L);
        if (optionalPerson.isPresent()) {
            Person foundPerson = optionalPerson.get();
            foundPerson.setDeleteDate(Date.from(Instant.now()).getTime());
            List<Address> addressList = foundPerson.getAddressList();
            for (Address a :
                    addressList) {
                a.setDeleteDate(Date.from(Instant.now()).getTime());
                addressRepository.save(a);
            }
            Person save = repository.save(foundPerson);
            return buildResponseOk(OK , save.getId());
        }
        return buildResponseOk(NOT_FOUND , "Person Not Found!");
    }

    @Override
    public BaseResponse<?> addPersonWithAddresses(AddPersonWithAddressesDto personDto) {
        List<String> addressesText = personDto.getAddresses();

        Person person = Person.builder()
                .username(personDto.getUsername())
                .phoneNumber(personDto.getPhoneNumber())
                .name(personDto.getName())
                .build();
        Person save = repository.save(person);
        for (String s :
                addressesText) {
            Address address = Address.builder()
                    .text(s)
                    .person(person)
                    .build();
            addressRepository.save(address);
        }
        return buildResponseOk(OK , save.getId());
    }
}

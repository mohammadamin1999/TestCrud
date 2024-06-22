package com.example.testcrud.repository;

import com.example.testcrud.model.Address;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AddressRepository extends JpaRepository<Address , Long> {

    Optional<Address> findAddressByIdAndDeleteDate(Long id , Long deleteDate);
}

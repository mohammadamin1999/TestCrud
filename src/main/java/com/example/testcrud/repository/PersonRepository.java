package com.example.testcrud.repository;

import com.example.testcrud.model.Person;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PersonRepository extends JpaRepository<Person, Long> {

    Optional<Person> findPersonById(Long id);

    Optional<Person> findPersonByIdAndDeleteDate(Long id , Long deleteDate);
}

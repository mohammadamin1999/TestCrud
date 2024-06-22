package com.example.testcrud.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Person extends BaseEntity{
    private String name;

    @Column(unique = true)
    private String username;

    @Column(unique = true)
    private String phoneNumber;

    @OneToOne
    private Person relatedPerson;

    @OneToMany
    private List<Address> addressList;
}

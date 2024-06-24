package com.example.testcrud.model.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Address extends BaseEntity{

    @Column(nullable = false)
    private String text;

    @ManyToOne
    @JoinColumn(name="person_id", nullable=false)
    private Person person;

}

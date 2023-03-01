package com.exercise.bankapplication.domain.client.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.experimental.SuperBuilder;

@AllArgsConstructor
@Getter
@Entity
@Inheritance(strategy = InheritanceType.JOINED)
@SuperBuilder
public class Person {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private  Long id;
    private  String name;
    private  String gender;
    private  int age;
    private  String address;
    private  String phone;
    public Person(){}
}

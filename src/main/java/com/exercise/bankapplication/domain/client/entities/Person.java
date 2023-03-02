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
    public static final String FEMALE = "F";
    public static final String MALE = "M";
    @Id
    @SequenceGenerator(name="person_seq", sequenceName = "person_seq", allocationSize=1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator="person_seq")
    private  Long id;
    private  String name;
    private  String gender;
    private  int age;
    private  String address;
    private  String phone;
    public Person(){}
}

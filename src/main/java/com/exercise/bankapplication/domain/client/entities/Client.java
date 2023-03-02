package com.exercise.bankapplication.domain.client.entities;


import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

@Getter
@Setter
@Entity
@SuperBuilder
public class Client extends Person {

    @Id
    @SequenceGenerator(name="client_seq", sequenceName = "client_seq", allocationSize=1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator="client_seq")
    private Long id;
    private  char[] password;
    private  boolean status;


    public Client(){}
}

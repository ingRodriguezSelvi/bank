package com.exercise.bankapplication.domain.client.entities;


import com.exercise.bankapplication.domain.client.exeptions.InvalidClientException;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

@Getter
@Setter
@Entity
public class Client extends Person {

    @Id
    @SequenceGenerator(name="client_seq", sequenceName = "client_seq", allocationSize=1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator="client_seq")
    private Long id;
    private  char[] password;
    private  boolean status;

    @Builder(builderMethodName = "clientBuilder")
    public Client(Long id, String name, String gender, int age, String address, String phone, char[] password, boolean status) {
        super(id, name, gender, age, address, phone);
        isValid(name, gender, age, address, phone, password);
        this.password = password;
        this.status = status;

    }

    private static void isValid(String name, String gender, int age, String address, String phone, char[] password) {
        if (    name == null || name.isEmpty() ||
                gender == null || gender.isEmpty() ||
                address == null || address.isEmpty() ||
                phone == null || phone.isEmpty() ||
                password == null || password.length == 0
            ) {
            throw new InvalidClientException("Faltan campos requeridos en la persona");
        }
        if (age < 18){
            throw new InvalidClientException("El cliente debe ser mayor de edad");
        }
        if (!gender.equals(Person.FEMALE) && !Person.MALE.equals(gender)){
            throw new InvalidClientException("El genero debe ser F o M");
        }
    }

    public static void isValid(Client client) {
        if (    client.getName() == null || client.getName().isEmpty() ||
                client.getGender() == null || client.getGender().isEmpty() ||
                client.getAddress() == null || client.getAddress().isEmpty() ||
                client.getPhone() == null || client.getPhone().isEmpty() ||
                client.getPassword() == null || client.getPassword().length == 0
        ) {
            throw new InvalidClientException("Faltan campos requeridos en la persona");
        }
        if (client.getAge() < 18){
            throw new InvalidClientException("El cliente debe ser mayor de edad");
        }
        if (!Person.FEMALE.equals(client.getGender()) && !Person.MALE.equals(client.getGender())){
            throw new InvalidClientException("El genero debe ser F o M");
        }
    }


    public Client(){}
}

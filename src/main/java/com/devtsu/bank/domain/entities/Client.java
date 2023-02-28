package com.devtsu.bank.domain.entities;

import lombok.*;


@Getter
public class Client extends Person {
    private final Long clientId;
    private final char[] password;
    private final boolean status;

    @Builder
    public Client(Long personId, String name, String gender, int age, String address, String phone, Long clientId, char[] password, boolean status) {
        super(personId, name, gender, age, address, phone);
        this.clientId = clientId;
        this.password = password;
        this.status = status;
    }
}

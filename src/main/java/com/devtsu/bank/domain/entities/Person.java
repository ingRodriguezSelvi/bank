package com.devtsu.bank.domain.entities;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class Person {
    private final Long personId;
    private final String name;
    private final String gender;
    private final int age;
    private final String address;
    private final String phone;
}

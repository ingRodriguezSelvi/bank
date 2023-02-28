package com.devtsu.bank.domain.entities;

import org.junit.Test;

import static org.junit.jupiter.api.Assertions.*;

public class PersonTest {

    @Test
    public void test_constructor_and_getters() {
        Person person = new Person(1L, "John", "Male", 30, "123 Main St", "555-1234");
        assertEquals(1L, person.getPersonId());
        assertEquals("John", person.getName());
        assertEquals("Male", person.getGender());
        assertEquals(30, person.getAge());
        assertEquals("555-1234", person.getPhone());
        assertEquals("123 Main St", person.getAddress());
    }
}

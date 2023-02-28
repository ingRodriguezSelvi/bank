package com.devtsu.bank.domain.entities;

import org.junit.Test;

import static org.junit.jupiter.api.Assertions.*;

public class ClientTest {

    @Test
    public void test_client() {
        char[] password = {'p', 'a', 's', 's', 'w', 'o', 'r', 'd'};
        Client client = Client.builder()
                .clientId(1L)
                .password(password)
                .personId(1L)
                .phone("xxx-phone-xxx")
                .age(18)
                .address("xxx-address-xxx")
                .gender("M")
                .name("xxx-name-xxx")
                .status(true)
                .build();

        assertEquals(1L, client.getPersonId());
        assertEquals("xxx-name-xxx", client.getName());
        assertEquals("M", client.getGender());
        assertEquals(18, client.getAge());
        assertEquals("xxx-address-xxx", client.getAddress());
        assertEquals("xxx-phone-xxx", client.getPhone());
        assertEquals(1L, client.getClientId());
        assertArrayEquals(password, client.getPassword());
        assertTrue(client.isStatus());
    }
}


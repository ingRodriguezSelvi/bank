package com.devtsu.bank.domain.services;

import com.devtsu.bank.domain.entities.Client;
import com.devtsu.bank.domain.repositories.ClientRepository;
import org.junit.Test;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import static org.mockito.ArgumentMatchers.any;


@Tag("unit")
@RunWith(MockitoJUnitRunner.class)
public class ClientServiceTest {

    @Mock
    ClientRepository repository;
    @InjectMocks
    ClientService service;

    @Test
    @DisplayName("Should call persist method on repository")
    public void testPersistOk() {
        this.service.create(Client.builder()
                .clientId(1L)
                .password("test".toCharArray())
                .personId(1L)
                .phone("xxx-phone-xxx")
                .age(18)
                .address("xxx-address-xxx")
                .gender("M")
                .name("xxx-name-xxx")
                .status(true)
                .build());

        Mockito.verify(repository, Mockito.times(1))
                .persist(any(Client.class));

    }

}

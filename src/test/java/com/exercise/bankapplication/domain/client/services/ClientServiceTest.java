package com.exercise.bankapplication.domain.client.services;

import com.exercise.bankapplication.domain.client.entities.Client;
import com.exercise.bankapplication.domain.client.exeptions.InvalidClientException;
import com.exercise.bankapplication.domain.client.repositories.ClientRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class ClientServiceTest {

    ClientService clientService;
    @Mock
    ClientRepository clientRepository;

    char[] password = {'p', 'a', 's', 's', 'w', 'o', 'r', 'd'};

    @Before
    public void setUp() {
        clientService = new ClientService(clientRepository);
    }

    @Test
    public void should_not_update_the_client_because_it_doesnt_exist() {
        Client client = Client.clientBuilder()
                .id(1L)
                .password(password)
                .status(true)
                .name("Andres")
                .gender("M")
                .age(18)
                .address("Av Domingo Mendez")
                .phone("4247335809")
                .build();
        RuntimeException exception = Assertions.assertThrows(RuntimeException.class, () -> {
            clientService.updateClient(client);
        });
        assertTrue(exception.getMessage().equals("El cliente no existe"));
    }

    @Test
    public void should_not_update_the_client_because_it_has_logical_deletion() {
        Client clientIn = Client.clientBuilder()
                .id(1L)
                .password(password)
                .status(false)
                .name("Andres")
                .gender("M")
                .age(18)
                .address("Av Domingo Mendez")
                .phone("4247335809")
                .build();
        clientIn.setId(1L);
        Client clientDb = Client.clientBuilder()
                .id(1L)
                .password(password)
                .status(true)
                .name("Andres")
                .gender("M")
                .age(18)
                .address("Av Domingo Mendez")
                .phone("4247335809")
                .build();
        clientDb.setId(1L);
        when(clientRepository.searchById(1L)).thenReturn(Optional.of(clientDb));

        RuntimeException exception = Assertions.assertThrows(RuntimeException.class, () -> {
            clientService.updateClient(clientIn);
        });
        assertTrue(exception.getMessage().equals("Esta intentando eliminar o activar un cliente, accion no permitida"));
    }

    @Test
    public void should_update_the_client() {
        Client clientIn = Client.clientBuilder()
                .id(1L)
                .password(password)
                .status(true)
                .name("Andres")
                .gender("M")
                .age(18)
                .address("Av Domingo Mendez")
                .phone("4247335809")
                .build();
        clientIn.setId(1L);
        when(clientRepository.searchById(1L)).thenReturn(Optional.of(clientIn));
        clientService.updateClient(clientIn);
        Mockito.verify(clientRepository).update(clientIn);
    }


    @Test
    public void should_create_a_client() {
        Client clientIn = Client.clientBuilder()
                .id(1L)
                .password(password)
                .status(true)
                .name("Andres")
                .gender("M")
                .age(18)
                .address("Av Domingo Mendez")
                .phone("4247335809")
                .build();
        clientService.createClient(clientIn);
        Mockito.verify(clientRepository).create(clientIn);


    }

}

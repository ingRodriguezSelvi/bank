package com.exercise.bankapplication.application.client.usecase;

import com.exercise.bankapplication.application.client.usecase.CreateClientUseCase;
import com.exercise.bankapplication.domain.client.entities.Client;
import com.exercise.bankapplication.domain.client.entities.Person;
import com.exercise.bankapplication.domain.client.services.ClientService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class CreateClientUseCaseTest {

    CreateClientUseCase createClientUseCase;
    @Mock
    ClientService clientService;

    @Test
    public void verify_call_create_client(){
        char[] password = {'p', 'a', 's', 's', 'w', 'o', 'r', 'd'};
        Client client = Client.clientBuilder()
                .password(password)
                .status(true)
                .name("TEST")
                .gender("M")
                .age(18)
                .address("XXX")
                .phone("XXXX")
                .build();
        createClientUseCase = new CreateClientUseCase(clientService);
        createClientUseCase.execute(client);
        Mockito.verify(clientService).createClient(client);

    }

}

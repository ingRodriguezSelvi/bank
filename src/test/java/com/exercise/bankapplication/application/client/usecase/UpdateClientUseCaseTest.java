package com.exercise.bankapplication.application.client.usecase;

import com.exercise.bankapplication.application.client.usecase.UpdateClientUseCase;
import com.exercise.bankapplication.domain.client.entities.Client;
import com.exercise.bankapplication.domain.client.services.ClientService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class UpdateClientUseCaseTest {

    UpdateClientUseCase updateClientUseCase;
    @Mock
    ClientService clientService;
    @Test
    public void veify_call_update_client(){
        char[] password = {'p', 'a', 's', 's', 'w', 'o', 'r', 'd'};
        Client client = Client.clientBuilder()
                .password(password)
                .status(true)
                .name("test")
                .gender("M")
                .age(18)
                .address("as")
                .phone("3434")
                .build();
        updateClientUseCase = new UpdateClientUseCase(clientService);
        updateClientUseCase.execute(client);
        Mockito.verify(clientService).updateClient(client);
    }
}

package com.exercise.bankapplication.application.client.usecase;

import com.exercise.bankapplication.domain.client.entities.Client;
import com.exercise.bankapplication.domain.client.services.ClientService;
import org.springframework.transaction.annotation.Transactional;
@Transactional
public class CreateClientUseCase {
    private final ClientService clientService;
    public CreateClientUseCase(ClientService clientService) {
        this.clientService = clientService;
    }

    public Client execute(Client client) {
       return clientService.createClient(client);
    }
}

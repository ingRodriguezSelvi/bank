package com.exercise.bankapplication.application.client.usecase;

import com.exercise.bankapplication.domain.client.entities.Client;
import com.exercise.bankapplication.domain.client.services.ClientService;

public class UpdateClientUseCase {

    private final ClientService clientService;

    public UpdateClientUseCase(ClientService clientService) {
        this.clientService = clientService;
    }

    public Client execute(Client client) {
       return clientService.updateClient(client);
    }
}

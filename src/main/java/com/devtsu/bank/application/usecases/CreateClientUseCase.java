package com.devtsu.bank.application.usecases;

import com.devtsu.bank.domain.entities.Client;
import com.devtsu.bank.domain.services.ClientService;
import org.springframework.stereotype.Service;

import javax.inject.Inject;
import javax.inject.Named;

@Service
public class CreateClientUseCase {
    private final ClientService clientService;

    public CreateClientUseCase(ClientService clientService){
        this.clientService = clientService;
    }

    public void execute(Client client){
        clientService.create(client);
    }
}

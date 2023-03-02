package com.exercise.bankapplication.domain.client.services;

import com.exercise.bankapplication.domain.client.entities.Client;
import com.exercise.bankapplication.domain.client.repositories.ClientRepository;

import java.util.Optional;

public class ClientService {

    private final ClientRepository clientRepository;

    public ClientService(ClientRepository clientRepository) {
        this.clientRepository = clientRepository;
    }

    public Client createClient(Client client) {
        if (client.getAge() < 18) {
            throw new RuntimeException("El cliente no debe ser menor a 18 años");
        }
        return clientRepository.create(client);
    }

    public Client updateClient(Client client)  {
        Optional<Client> clientDB = clientRepository.searchById(client.getId());
        if (clientDB.isEmpty()){
            throw new RuntimeException("El cliente no existe");
        }
        if (clientDB.get().isStatus() != client.isStatus()){
            throw new RuntimeException("Esta intentando eliminar o activar un cliente, accion no permitida");
        }
        return clientRepository.update(client);
    }

    public Client searchById(Long id){
        Optional<Client> clientOptional = clientRepository.searchById(id);
        if (clientOptional.isEmpty()){
            throw new RuntimeException("El cliente no existe");
        }
        return clientOptional.get();
    }
}

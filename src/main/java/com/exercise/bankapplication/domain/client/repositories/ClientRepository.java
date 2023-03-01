package com.exercise.bankapplication.domain.client.repositories;

import com.exercise.bankapplication.domain.client.entities.Client;

import java.util.Optional;

public interface ClientRepository {

    Client create(Client client);

    Client update(Client client);

    Optional<Client> searchById(Long id);
}

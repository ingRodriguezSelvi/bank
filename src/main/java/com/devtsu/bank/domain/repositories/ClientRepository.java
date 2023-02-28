package com.devtsu.bank.domain.repositories;

import com.devtsu.bank.domain.entities.Client;

import javax.inject.Named;
import java.util.Optional;
public interface ClientRepository {

    Long persist(Client client);

    Optional<Client> findByClientId(Long clientId);
}

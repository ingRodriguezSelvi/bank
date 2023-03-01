package com.exercise.bankapplication.infra.persitence;


import com.exercise.bankapplication.domain.client.entities.Client;
import com.exercise.bankapplication.domain.client.repositories.ClientRepository;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;

import org.springframework.data.jpa.repository.support.SimpleJpaRepository;

import java.util.Optional;

public class PostgresqlClientRepository extends SimpleJpaRepository<Client, Long> implements ClientRepository {

    public PostgresqlClientRepository(EntityManager em) {
        super(Client.class, em);
    }


    @Override
    public Client create(Client client) {
       return this.save(client);
    }

    @Override
    public Client update(Client client) {
        return this.save(client);
    }

    @Override
    public Optional<Client> searchById(Long id) {
        return this.findById(id);
    }


}

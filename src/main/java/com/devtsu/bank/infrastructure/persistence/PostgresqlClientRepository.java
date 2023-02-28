package com.devtsu.bank.infrastructure.persistence;

import com.devtsu.bank.domain.entities.Client;
import com.devtsu.bank.domain.repositories.ClientRepository;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.data.jpa.repository.support.SimpleJpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public class PostgresqlClientRepository extends SimpleJpaRepository<Client, Long> implements ClientRepository{
    @PersistenceContext
    private final EntityManager entityManager;

    public PostgresqlClientRepository(EntityManager em) {
        super(Client.class, em);
        this.entityManager = em;
    }

    @Override
    public Long persist(Client client) {
        this.save(client);
        return client.getClientId();
    }

    @Override
    public Optional<Client> findByClientId(Long clientId) {
        return this.findById(clientId);
    }
}

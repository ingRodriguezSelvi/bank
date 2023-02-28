package com.devtsu.bank.domain.services;

import com.devtsu.bank.domain.entities.Client;
import com.devtsu.bank.domain.repositories.ClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.inject.Inject;
import javax.inject.Named;

@Service
public class ClientService {
    @Autowired
    ClientRepository repository;


    public void create(Client client) {
        this.repository.persist(client);
    }
}

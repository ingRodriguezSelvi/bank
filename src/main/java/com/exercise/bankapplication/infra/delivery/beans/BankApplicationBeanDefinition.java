package com.exercise.bankapplication.infra.delivery.beans;

import com.exercise.bankapplication.application.client.usecase.CreateClientUseCase;
import com.exercise.bankapplication.application.client.usecase.UpdateClientUseCase;
import com.exercise.bankapplication.domain.client.repositories.ClientRepository;
import com.exercise.bankapplication.domain.client.services.ClientService;
import com.exercise.bankapplication.infra.persitence.PostgresqlClientRepository;
import jakarta.persistence.EntityManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class BankApplicationBeanDefinition {

    @Bean
    public CreateClientUseCase createClientUseCase(ClientService clientService){
        return new CreateClientUseCase(clientService);
    }

    @Bean
    public UpdateClientUseCase updateClientUseCase(ClientService clientService){
        return new UpdateClientUseCase(clientService);
    }

    @Bean
    public ClientService clientService(ClientRepository clientRepository){
        return new ClientService(clientRepository);
    }

    @Bean
    public ClientRepository clientRepository(EntityManager entityManager){
        return new PostgresqlClientRepository(entityManager);
    }

}

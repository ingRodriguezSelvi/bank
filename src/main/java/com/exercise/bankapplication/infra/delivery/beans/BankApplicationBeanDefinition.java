package com.exercise.bankapplication.infra.delivery.beans;

import com.exercise.bankapplication.application.bankaccount.usecase.CreateBankAccountUseCase;
import com.exercise.bankapplication.application.bankaccount.usecase.CreateTransactionsUseCase;
import com.exercise.bankapplication.application.bankaccount.usecase.GetTransactionsByDateAndUserUseCase;
import com.exercise.bankapplication.application.client.usecase.CreateClientUseCase;
import com.exercise.bankapplication.application.client.usecase.UpdateClientUseCase;
import com.exercise.bankapplication.domain.bankaccount.repositories.BankAccountRepository;
import com.exercise.bankapplication.domain.bankaccount.repositories.TransactionRepository;
import com.exercise.bankapplication.domain.bankaccount.services.BankAccountService;
import com.exercise.bankapplication.domain.client.repositories.ClientRepository;
import com.exercise.bankapplication.domain.client.services.ClientService;
import com.exercise.bankapplication.infra.persitence.PostgresqlBankAccountRepository;
import com.exercise.bankapplication.infra.persitence.PostgresqlClientRepository;
import com.exercise.bankapplication.infra.persitence.PostgresqlTransactionRepository;
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
    public CreateBankAccountUseCase createBankAccountUseCase(BankAccountService bankAccountService, ClientService clientService){
        return new CreateBankAccountUseCase(bankAccountService, clientService);
    }

    @Bean
    public CreateTransactionsUseCase createTransactionsUseCase(BankAccountService bankAccountService){
        return new CreateTransactionsUseCase(bankAccountService);
    }

    @Bean
    public GetTransactionsByDateAndUserUseCase getTransactionsByDateAndUserUseCase(BankAccountService bankAccountService, ClientService clientService){
        return new GetTransactionsByDateAndUserUseCase(bankAccountService, clientService);
    }

    @Bean
    public BankAccountService bankAccountService(BankAccountRepository bankAccountRepository, TransactionRepository transactionRepository){
        return new BankAccountService(bankAccountRepository, transactionRepository);
    }

    @Bean
    public BankAccountRepository bankAccountRepository(EntityManager entityManager){
        return new PostgresqlBankAccountRepository(entityManager);
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

    @Bean
    public TransactionRepository transactionRepository(EntityManager entityManager){
        return new PostgresqlTransactionRepository(entityManager);
    }

}

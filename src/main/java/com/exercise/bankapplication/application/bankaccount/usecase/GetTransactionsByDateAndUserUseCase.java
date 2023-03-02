package com.exercise.bankapplication.application.bankaccount.usecase;

import com.exercise.bankapplication.application.bankaccount.dto.TransactionDTO;
import com.exercise.bankapplication.domain.bankaccount.entities.BankAccount;
import com.exercise.bankapplication.domain.bankaccount.entities.Transaction;
import com.exercise.bankapplication.domain.bankaccount.entities.TransactionListDTO;
import com.exercise.bankapplication.domain.bankaccount.services.BankAccountService;
import com.exercise.bankapplication.domain.client.entities.Client;
import com.exercise.bankapplication.domain.client.services.ClientService;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;

@Transactional
public class GetTransactionsByDateAndUserUseCase {
    private final BankAccountService bankAccountService;
    private final ClientService clientService;
    public GetTransactionsByDateAndUserUseCase(BankAccountService bankAccountService, ClientService clientService) {
        this.bankAccountService = bankAccountService;
        this.clientService = clientService;
    }
    public List<TransactionListDTO> execute(LocalDate date, Long clientId){
        Client client = clientService.findById(clientId);
        BankAccount bankAccount = bankAccountService.findByClientId(client.getId());
        List<Transaction> transactions = bankAccountService.getTransactionsByDateAndAccountId(date, bankAccount.getId());
        return transactions.stream().map(transaction -> new TransactionListDTO(client, bankAccount, transaction)).toList();
    }
}

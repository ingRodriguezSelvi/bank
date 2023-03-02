package com.exercise.bankapplication.application.bankaccount.usecase;

import com.exercise.bankapplication.application.bankaccount.dto.BankAccountDTO;
import com.exercise.bankapplication.domain.bankaccount.entities.BankAccount;
import com.exercise.bankapplication.domain.bankaccount.services.BankAccountService;
import com.exercise.bankapplication.domain.client.entities.Client;
import com.exercise.bankapplication.domain.client.services.ClientService;
import org.springframework.transaction.annotation.Transactional;

@Transactional
public class CreateBankAccountUseCase {

    private final BankAccountService bankAccountService;
    private final ClientService clientService;

    public CreateBankAccountUseCase(BankAccountService bankAccountService, ClientService clientService) {
        this.bankAccountService = bankAccountService;
        this.clientService = clientService;
    }

    public BankAccountDTO execute(BankAccount bankAccount) {
        Client client = clientService.searchById(bankAccount.getClientId());
        BankAccount bankAccountDb = bankAccountService.create(bankAccount);
        return BankAccountDTO.builder()
                .nameClient(client.getName())
                .typeAccount(bankAccountDb.getType())
                .status(bankAccountDb.isStatus())
                .balanceStart(bankAccountDb.getStartBalance())
                .numberAccount(bankAccountDb.getNumber())
                .build();
    }
}

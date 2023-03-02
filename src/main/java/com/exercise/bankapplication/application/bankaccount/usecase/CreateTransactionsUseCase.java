package com.exercise.bankapplication.application.bankaccount.usecase;

import com.exercise.bankapplication.domain.bankaccount.entities.Transaction;
import com.exercise.bankapplication.domain.bankaccount.services.BankAccountService;
import org.springframework.transaction.annotation.Transactional;

@Transactional
public class CreateTransactionsUseCase {

    private final BankAccountService bankAccountService;

    public CreateTransactionsUseCase(BankAccountService bankAccountService) {
        this.bankAccountService = bankAccountService;
    }

    public Transaction execute(Transaction transaction){
        return bankAccountService.createTransaction(transaction);
    }
}

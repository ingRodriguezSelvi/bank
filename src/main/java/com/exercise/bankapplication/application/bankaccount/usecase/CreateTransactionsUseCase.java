package com.exercise.bankapplication.application.bankaccount.usecase;

import com.exercise.bankapplication.application.bankaccount.dto.TransactionDTO;
import com.exercise.bankapplication.domain.bankaccount.entities.BankAccount;
import com.exercise.bankapplication.domain.bankaccount.entities.Transaction;
import com.exercise.bankapplication.domain.bankaccount.services.BankAccountService;

import org.springframework.transaction.annotation.Transactional;

@Transactional
public class CreateTransactionsUseCase {

    private final BankAccountService bankAccountService;

    public CreateTransactionsUseCase(BankAccountService bankAccountService) {
        this.bankAccountService = bankAccountService;
    }

    public TransactionDTO execute(Transaction transaction){
        BankAccount bankAccount = bankAccountService.findById(transaction.getAccountId());
        Transaction transactionDb = bankAccountService.createTransaction(transaction);
        return new TransactionDTO(transactionDb, bankAccount);
    }
}

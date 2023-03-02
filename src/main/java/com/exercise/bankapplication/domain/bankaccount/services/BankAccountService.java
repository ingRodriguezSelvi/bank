package com.exercise.bankapplication.domain.bankaccount.services;

import com.exercise.bankapplication.domain.bankaccount.entities.BankAccount;
import com.exercise.bankapplication.domain.bankaccount.entities.Transaction;
import com.exercise.bankapplication.domain.bankaccount.repositories.BankAccountRepository;
import com.exercise.bankapplication.domain.bankaccount.repositories.TransactionRepository;

import java.util.List;
import java.util.Optional;

public class BankAccountService {
    private final BankAccountRepository bankAccountRepository;
    private final TransactionRepository transactionRepository;

    public BankAccountService(BankAccountRepository bankAccountRepository, TransactionRepository transactionRepository) {
        this.bankAccountRepository = bankAccountRepository;
        this.transactionRepository = transactionRepository;
    }

    public BankAccount create(BankAccount bankAccount) {
        return bankAccountRepository.create(bankAccount);
    }

    public Transaction createTransaction(Transaction transaction) {
        Optional<BankAccount> bankAccountOpt = bankAccountRepository.searchById(transaction.getAccountId());
        if (bankAccountOpt.isEmpty()) {
            throw new IllegalArgumentException("La cuenta no existe");
        }
        BankAccount bankAccount = bankAccountOpt.get();
        verifyExceeded(transaction);
        if (transaction.getType().equals(Transaction.DEPOSITO)) {
            transaction.deposit(getBalance(bankAccount));
        } else {
            transaction.withdraw(getBalance(bankAccount));
        }

        return transactionRepository.create(transaction);
    }

    private double getBalance(BankAccount bankAccount) {
        List<Transaction> transactions = transactionRepository.searchByAccountId(bankAccount.getId());
        double withdraws = summarizesTransactions(transactions, Transaction.RETIRO);
        double deposits = summarizesTransactions(transactions, Transaction.DEPOSITO);
        return bankAccount.getStartBalance() + deposits - withdraws;
    }

    private double summarizesTransactions(List<Transaction> transactions, String type) {
        return transactions
                .stream()
                .filter(transaction -> type.equals(transaction.getType()))
                .mapToDouble(Transaction::getValue)
                .sum();
    }

    private void verifyExceeded(Transaction transaction) {
        List<Transaction> transactionList = transactionRepository.searchByDateAndAccountId(transaction.getDate(), transaction.getAccountId());
        double balance = transactionList
                .stream()
                .filter(transactionLambda -> transactionLambda.getType().equals(Transaction.RETIRO))
                .mapToDouble(Transaction::getValue)
                .sum();
        if (balance + transaction.getValue() > 1000) {
            throw new IllegalArgumentException("Cupo diario Excedido");
        }
    }


}

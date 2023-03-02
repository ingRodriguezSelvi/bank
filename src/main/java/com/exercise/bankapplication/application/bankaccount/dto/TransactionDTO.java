package com.exercise.bankapplication.application.bankaccount.dto;

import com.exercise.bankapplication.domain.bankaccount.entities.BankAccount;
import com.exercise.bankapplication.domain.bankaccount.entities.Transaction;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TransactionDTO {
    Integer number;
    String type;
    double balanceStart;
    boolean status;
    String transaction;

    public TransactionDTO(Transaction transaction, BankAccount bankAccount) {
        this.number = bankAccount.getNumber();
        this.type = bankAccount.getType();
        this.balanceStart = bankAccount.getStartBalance();
        this.status = bankAccount.isStatus();
        this.transaction = transaction.getType() + " " + transaction.getValue();
    }
}

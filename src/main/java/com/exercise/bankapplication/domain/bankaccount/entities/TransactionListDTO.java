package com.exercise.bankapplication.domain.bankaccount.entities;

import com.exercise.bankapplication.domain.client.entities.Client;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
public class TransactionListDTO {
    LocalDate date;
    String clientName;
    Integer numberAccount;
    String typeAccount;

    String typeTransaction;
    double balanceStart;
    boolean status;
    double valueTransaction;
    double balanceEnd;
    public TransactionListDTO(Client client, BankAccount bankAccount, Transaction transaction) {
        this.date = transaction.getDate();
        this.clientName = client.getName();
        this.numberAccount = bankAccount.getNumber();
        this.typeAccount = bankAccount.getType();
        this.balanceStart = bankAccount.getStartBalance();
        this.status = bankAccount.isStatus();
        this.valueTransaction = transaction.getValue();
        this.typeTransaction = transaction.getType();
        this.balanceEnd =  transaction.getBalance();
    }
}

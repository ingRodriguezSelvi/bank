package com.exercise.bankapplication.domain.bankaccount.entities;

import com.exercise.bankapplication.domain.bankaccount.exeptions.InvalidTransactionBankException;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@Entity
public class Transaction {

    public static final String DEPOSIT = "deposit";
    public static final String WITHDRAW = "withdraw";
    @Id
    @SequenceGenerator(name="transaction_seq", sequenceName = "transaction_seq", allocationSize=1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator="transaction_seq")
    private Long id;
    private LocalDate date;
    private String type;
    private double value;
    private double balance;

    private Long accountId;
    @Builder
    public Transaction(LocalDate date, String type, double value, Long accountId) {
        isValid(date, type, value, accountId);
        this.date = date;
        this.type = type;
        this.value = value;
        this.accountId = accountId;
    }

    private static void isValid(LocalDate date, String type, double value, Long accountId) {
        if( type == null || !type.equals(DEPOSIT) && !type.equals(WITHDRAW)) {
            throw new InvalidTransactionBankException("Tipo de movimiento no permitido");
        }
        if(date == null){
            throw new InvalidTransactionBankException("La fecha es requerida");
        }
        if(accountId == null){
            throw new InvalidTransactionBankException("La cuenta es requerida");
        }
    }
    public static void isValid(Transaction transaction) {
        if( transaction.getType() == null || !DEPOSIT.equals(transaction.getType()) && !WITHDRAW.equals(transaction.getType())) {
            throw new InvalidTransactionBankException("Tipo de movimiento no permitido");
        }
        if(transaction.getDate() == null){
            throw new InvalidTransactionBankException("La fecha es requerida");
        }
        if(transaction.getAccountId() == null){
            throw new InvalidTransactionBankException("La cuenta es requerida");
        }
    }

    public void deposit(double balanceHold){
        this.balance = balanceHold + this.value;
    }

    public void withdraw(double balanceHold){
        if (balanceHold <= 0 || this.value > balanceHold){
            throw new IllegalArgumentException("Saldo no disponible");
        }
        this.balance = balanceHold - this.value;
    }

    public Transaction() {

    }
}

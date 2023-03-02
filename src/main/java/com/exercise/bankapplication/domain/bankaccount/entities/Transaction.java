package com.exercise.bankapplication.domain.bankaccount.entities;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@Entity
public class Transaction {

    public static final String DEPOSITO = "deposito";
    public static final String RETIRO = "retiro";
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
        if( type== null || !type.equals(DEPOSITO) && !type.equals(RETIRO)) {
            throw new IllegalArgumentException("Tipo de movimiento no permitido");
        }
        if(value > 1000){
            throw new IllegalArgumentException("Cupo diario Excedido");
        }
        if(date == null){
            throw new IllegalArgumentException("La fecha es requerida");
        }
        if(accountId == null){
            throw new IllegalArgumentException("Numero de cuenta es requerido");
        }
        this.date = date;
        this.type = type;
        this.value = value;
        this.accountId = accountId;
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

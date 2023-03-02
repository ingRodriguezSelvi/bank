package com.exercise.bankapplication.domain.bankaccount.entities;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name="bank_account")
public class BankAccount {

    public static final String AHORRO = "ahorro";
    public static final String CORRIENTE = "corriente";

    @Id
    @SequenceGenerator(name="bank_account_seq", sequenceName = "bank_account_seq", allocationSize=1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator="bank_account_seq")
    private Long id;
    private Integer number;
    private String type;
    private double startBalance;
    private boolean status;
    private long clientId;

    @Builder
    private BankAccount(Integer number, String type, double startBalance, boolean status, long clientId) {
        if( type== null || !type.equals(AHORRO) && !type.equals(CORRIENTE)) {
            throw new IllegalArgumentException("Tipo de cuenta no válido");
        }
        if (clientId == 0) {
            throw new IllegalArgumentException("El clientId no puede ser nulo");
        }
        if ( startBalance < 0 ){
            throw new IllegalArgumentException("El monto de apertura no puede ser menor a 1$");
        }
        this.number = number;
        this.type = type;
        this.startBalance = startBalance;
        this.status = status;
        this.clientId = clientId;
    }

    public BankAccount(){}
}


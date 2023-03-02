package com.exercise.bankapplication.domain.bankaccount.entities;

import com.exercise.bankapplication.domain.bankaccount.exeptions.InvalidBankAccountException;
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
        isValid(type, startBalance, clientId);
        this.number = number;
        this.type = type;
        this.startBalance = startBalance;
        this.status = status;
        this.clientId = clientId;
    }

    private static void isValid(String type, double startBalance, long clientId) {
        if( type == null || !type.equals(AHORRO) && !type.equals(CORRIENTE)) {
            throw new InvalidBankAccountException("Tipo de cuenta no válido");
        }
        if (clientId == 0) {
            throw new InvalidBankAccountException("El clientId no puede ser nulo");
        }
        if ( startBalance < 0 ){
            throw new InvalidBankAccountException("El monto de apertura no puede ser menor a 1$");
        }
    }

    public static void isValid(BankAccount bankAccount) {
        if( bankAccount.getType() == null || !AHORRO.equals(bankAccount.getType()) && !CORRIENTE.equals(bankAccount.getType())) {
            throw new InvalidBankAccountException("Tipo de cuenta no válido");
        }
        if (bankAccount.getClientId() == 0) {
            throw new InvalidBankAccountException("El clientId no puede ser nulo");
        }
        if ( bankAccount.getStartBalance() < 0 ){
            throw new InvalidBankAccountException("El monto de apertura no puede ser menor a 1$");
        }
    }

    public BankAccount(){}
}


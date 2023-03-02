package com.exercise.bankapplication.domain.bankaccount.exeptions;

public class InvalidTransactionBankException extends RuntimeException {
    public InvalidTransactionBankException(String message) {
        super(message);
    }
}

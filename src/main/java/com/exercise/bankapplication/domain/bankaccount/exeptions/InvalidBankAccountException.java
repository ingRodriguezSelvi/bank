package com.exercise.bankapplication.domain.bankaccount.exeptions;

public class InvalidBankAccountException extends RuntimeException {
    public InvalidBankAccountException(String message) {
        super(message);
    }
}

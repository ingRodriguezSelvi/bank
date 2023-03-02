package com.exercise.bankapplication.domain.bankaccount.entities;

import org.junit.Test;

import static org.junit.jupiter.api.Assertions.*;

public class BankAccountTest {

    @Test
    public void verify_type_account(){
        BankAccount account = BankAccount.builder()
                .number(123456789)
                .type(BankAccount.AHORRO)
                .startBalance(1000.00)
                .status(true)
                .clientId(1L)
                .build();
        assertEquals(account.getType(),"ahorro");
    }
}

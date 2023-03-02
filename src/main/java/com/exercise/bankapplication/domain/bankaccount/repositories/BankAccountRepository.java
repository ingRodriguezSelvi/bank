package com.exercise.bankapplication.domain.bankaccount.repositories;

import com.exercise.bankapplication.domain.bankaccount.entities.BankAccount;

import java.util.Optional;

public interface BankAccountRepository {

    BankAccount create(BankAccount bankAccount);
    BankAccount update(BankAccount bankAccount);

    Optional<BankAccount> searchById(Long id);
}

package com.exercise.bankapplication.domain.bankaccount.repositories;

import com.exercise.bankapplication.domain.bankaccount.entities.Transaction;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface TransactionRepository {

    Transaction create(Transaction transaction);
    Transaction update(Transaction transaction);
    Optional<Transaction> searchById(Long id);

    List<Transaction> searchByAccountId(Long accountId);
    List<Transaction> searchByDateAndAccountId(LocalDate localDate,Long accountId);
}

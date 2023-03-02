package com.exercise.bankapplication.infra.persitence;


import com.exercise.bankapplication.domain.bankaccount.entities.BankAccount;
import com.exercise.bankapplication.domain.bankaccount.repositories.BankAccountRepository;
import jakarta.persistence.EntityManager;
import org.springframework.data.jpa.repository.support.SimpleJpaRepository;

import java.util.Optional;

public class PostgresqlBankAccountRepository extends SimpleJpaRepository<BankAccount, Long> implements BankAccountRepository {

    public PostgresqlBankAccountRepository(EntityManager em) {
        super(BankAccount.class, em);
    }


    @Override
    public BankAccount create(BankAccount bankAccount) {
       return this.save(bankAccount);
    }

    @Override
    public BankAccount update(BankAccount bankAccount) {
        return this.save(bankAccount);
    }

    @Override
    public Optional<BankAccount> searchById(Long id) {
        return this.findById(id);
    }


}

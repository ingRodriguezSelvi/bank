package com.exercise.bankapplication.infra.persitence;


import com.exercise.bankapplication.domain.bankaccount.entities.BankAccount;
import com.exercise.bankapplication.domain.bankaccount.entities.Transaction;
import com.exercise.bankapplication.domain.bankaccount.repositories.BankAccountRepository;
import com.exercise.bankapplication.domain.bankaccount.repositories.TransactionRepository;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;
import org.springframework.data.jpa.repository.support.SimpleJpaRepository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public class PostgresqlTransactionRepository extends SimpleJpaRepository<Transaction, Long> implements TransactionRepository {

    @PersistenceContext
    private EntityManager entityManager;
    public PostgresqlTransactionRepository(EntityManager em) {
        super(Transaction.class, em);
    }


    @Override
    public Transaction create(Transaction transaction) {
       return this.save(transaction);
    }

    @Override
    public Transaction update(Transaction transaction) {
        return this.save(transaction);
    }

    @Override
    public Optional<Transaction> searchById(Long id) {
        return this.findById(id);
    }

    @Override
    public List<Transaction> searchByAccountId(Long accountId) {
        String jpql = "SELECT t FROM Transaction t WHERE t.accountId = :accountId";
        TypedQuery<Transaction> query = entityManager.createQuery(jpql, Transaction.class);
        query.setParameter("accountId", accountId);
        return query.getResultList();
    }


    @Override
    public List<Transaction> searchByDateAndAccountId(LocalDate localDate,Long accountId) {
        String jpql = "SELECT t FROM Transaction t WHERE t.date = :localDate AND t.accountId = :accountId";
        TypedQuery<Transaction> query = entityManager.createQuery(jpql, Transaction.class);
        query.setParameter("localDate", localDate);
        query.setParameter("accountId", accountId);
        return query.getResultList();
    }
}

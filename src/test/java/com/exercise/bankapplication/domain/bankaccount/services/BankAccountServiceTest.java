package com.exercise.bankapplication.domain.bankaccount.services;

import com.exercise.bankapplication.domain.bankaccount.entities.BankAccount;
import com.exercise.bankapplication.domain.bankaccount.entities.Transaction;
import com.exercise.bankapplication.domain.bankaccount.repositories.BankAccountRepository;
import com.exercise.bankapplication.domain.bankaccount.repositories.TransactionRepository;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.when;
@RunWith(MockitoJUnitRunner.class)
public class BankAccountServiceTest {

    BankAccountService bankAccountService;

    @Mock
    BankAccountRepository bankAccountRepository;

    @Mock
    TransactionRepository transactionRepository;

    @Test
    public void deposit() {
        bankAccountService = new BankAccountService(bankAccountRepository, transactionRepository);
        Transaction transaction = Transaction.builder()
                .accountId(10L)
                .date(LocalDate.of(2023, 5, 18))
                .value(500)
                .type(Transaction.DEPOSIT)
                .build();
        BankAccount account = BankAccount.builder()
                .number(123456789)
                .type(BankAccount.AHORRO)
                .startBalance(1000.00)
                .status(true)
                .clientId(1L)
                .build();
        when(bankAccountRepository.searchById(anyLong())).thenReturn(Optional.of(account));
        when(transactionRepository.create(any(Transaction.class))).thenReturn(transaction);
       Transaction transactionDb = bankAccountService.createTransaction(transaction);
       assertEquals(1500, transactionDb.getBalance());
    }

    @Test
    public void withdraw_successful() {
        bankAccountService = new BankAccountService(bankAccountRepository, transactionRepository);
        Transaction transactionHold = Transaction.builder()
                .accountId(10L)
                .date(LocalDate.of(2023, 5, 18))
                .value(50)
                .type(Transaction.WITHDRAW)
                .build();
        Transaction transaction = Transaction.builder()
                .accountId(10L)
                .date(LocalDate.of(2023, 5, 18))
                .value(500)
                .type(Transaction.WITHDRAW)
                .build();
        BankAccount account = BankAccount.builder()
                .number(123456789)
                .type(BankAccount.AHORRO)
                .startBalance(600.00)
                .status(true)
                .clientId(1L)
                .build();
        when(bankAccountRepository.searchById(anyLong())).thenReturn(Optional.of(account));
        when(transactionRepository.searchByAccountId(null)).thenReturn(Arrays.asList(transactionHold));
        when(transactionRepository.create(any(Transaction.class))).thenReturn(transaction);
        Transaction transactionDb = bankAccountService.createTransaction(transaction);
        assertEquals(50, transactionDb.getBalance());
    }

    @Test
    public void should_decline_the_balance_withdrawal(){
        bankAccountService = new BankAccountService(bankAccountRepository, transactionRepository);
        Transaction transactionHold = Transaction.builder()
                .accountId(10L)
                .date(LocalDate.of(2023, 5, 18))
                .value(100)
                .type(Transaction.WITHDRAW)
                .build();
        Transaction transaction = Transaction.builder()
                .accountId(10L)
                .date(LocalDate.of(2023, 5, 18))
                .value(550)
                .type(Transaction.WITHDRAW)
                .build();
        BankAccount account = BankAccount.builder()
                .number(123456789)
                .type(BankAccount.AHORRO)
                .startBalance(600.00)
                .status(true)
                .clientId(1L)
                .build();
        when(bankAccountRepository.searchById(anyLong())).thenReturn(Optional.of(account));
        when(transactionRepository.searchByAccountId(null)).thenReturn(Arrays.asList(transactionHold));
        RuntimeException exception = Assertions.assertThrows(RuntimeException.class, () -> {
            bankAccountService.createTransaction(transaction);
        });
        assertTrue(exception.getMessage().equals("Saldo no disponible"));
    }

    @Test
    public void should_withdraw_failed() {
        bankAccountService = new BankAccountService(bankAccountRepository, transactionRepository);
        Transaction transactionHold = Transaction.builder()
                .accountId(10L)
                .date(LocalDate.of(2023, 5, 18))
                .value(1000)
                .type(Transaction.WITHDRAW)
                .build();
        Transaction transaction = Transaction.builder()
                .accountId(10L)
                .date(LocalDate.of(2023, 5, 18))
                .value(500)
                .type(Transaction.WITHDRAW)
                .build();
        BankAccount account = BankAccount.builder()
                .number(123456789)
                .type(BankAccount.AHORRO)
                .startBalance(600.00)
                .status(true)
                .clientId(1L)
                .build();
        when(bankAccountRepository.searchById(anyLong())).thenReturn(Optional.of(account));
        when(transactionRepository.searchByDateAndAccountId(any(LocalDate.class),anyLong())).thenReturn(Arrays.asList(transactionHold));
        RuntimeException exception = Assertions.assertThrows(RuntimeException.class, () -> {
            bankAccountService.createTransaction(transaction);
        });
        assertTrue(exception.getMessage().equals("Cupo diario Excedido"));
    }

}

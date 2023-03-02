package com.exercise.bankapplication.integrations;

import com.exercise.bankapplication.BankApplication;
import com.exercise.bankapplication.application.bankaccount.dto.TransactionDTO;
import com.exercise.bankapplication.application.bankaccount.usecase.CreateTransactionsUseCase;
import com.exercise.bankapplication.domain.bankaccount.entities.Transaction;
import com.exercise.bankapplication.integrations.configIntegralTest.ConfigIntegralTest;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.SqlGroup;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.LocalDate;
public class CreateTransactionsUseCaseTest extends ConfigIntegralTest {
    @Autowired
    CreateTransactionsUseCase createTransactionsUseCase;
    @Test()
    @SqlGroup({
            @Sql(value = "/sql/data_to_create_transaction.sql"),
            @Sql(value = "/sql/CLEAR_DATA_BASE.sql", executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD),
    })
    public void should_create_a_client_correctly() {
        Transaction transaction = Transaction.builder()
                .date(LocalDate.of(2023,2,3))
                .type(Transaction.WITHDRAW)
                .value(575)
                .accountId(100L)
                .build();
        TransactionDTO transactionPersist = createTransactionsUseCase.execute(transaction);
        Assert.assertNotNull(transactionPersist);
        Assert.assertEquals(transactionPersist.getTransaction(), transaction.getType() + " " + transaction.getValue());
    }
}

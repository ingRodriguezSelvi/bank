package com.exercise.bankapplication.integrations;

import com.exercise.bankapplication.BankApplication;
import com.exercise.bankapplication.application.bankaccount.dto.BankAccountDTO;
import com.exercise.bankapplication.application.bankaccount.usecase.CreateBankAccountUseCase;
import com.exercise.bankapplication.application.client.usecase.CreateClientUseCase;
import com.exercise.bankapplication.domain.bankaccount.entities.BankAccount;
import com.exercise.bankapplication.domain.client.entities.Client;
import com.exercise.bankapplication.integrations.configIntegralTest.ConfigIntegralTest;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = {BankApplication.class})
public class CreateBankAccountUseTest extends ConfigIntegralTest {

    @Autowired
    CreateBankAccountUseCase createBankAccountUseCase;
    @Autowired
    CreateClientUseCase createClientUseCase;

    @Test()
    public void should_create_a_bank_account_correctly() {
        char[] password = {'1', '2', '3', '4'};
        Client clientIn = Client.builder()
                .password(password)
                .status(true)
                .name("Jose Lema")
                .gender("M")
                .age(18)
                .address("Otavalo sn y principal")
                .phone("098254785")
                .build();
        createClientUseCase.execute(clientIn);
        BankAccount bankAccount = BankAccount.builder()
                .clientId(1L)
                .number(478758)
                .type(BankAccount.AHORRO)
                .startBalance(2000)
                .status(true)
                .build();

        BankAccountDTO bankAccountDTO = createBankAccountUseCase.execute(bankAccount);
        Assert.assertEquals(bankAccountDTO.getNameClient(), clientIn.getName());
    }
}

package com.exercise.bankapplication.integrations;

import com.exercise.bankapplication.BankApplication;
import com.exercise.bankapplication.application.client.usecase.CreateClientUseCase;
import com.exercise.bankapplication.domain.client.entities.Client;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = {BankApplication.class})
public class CreateClientTest {

    private static final MyPostgresqlContainer postgresSQLContainer = MyPostgresqlContainer.getInstance();

    @DynamicPropertySource
    static void setProperties(DynamicPropertyRegistry registry) {

        registry.add("spring.datasource.url", () -> postgresSQLContainer.getJdbcUrl());
        registry.add("spring.datasource.username", postgresSQLContainer::getUsername);
        registry.add("spring.datasource.password", postgresSQLContainer::getPassword);
        registry.add("spring.flyway.enabled", () -> true);

    }

    @Autowired
    CreateClientUseCase createClientUseCase;

    @Test()
    public void should_create_a_client_correctly() {
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
        Client clientPersist = createClientUseCase.execute(clientIn);
        Assert.assertEquals(clientPersist.getPassword(), password);
        Assert.assertEquals(clientPersist.isStatus(), true);
        Assert.assertEquals(clientPersist.getName(), "Jose Lema");
        Assert.assertEquals(clientPersist.getGender(), "M");
        Assert.assertEquals(clientPersist.getAge(), 18);
        Assert.assertEquals(clientPersist.getAddress(), "Otavalo sn y principal");
        Assert.assertEquals(clientPersist.getPhone(), "098254785");
    }
}

package com.devtsu.bank.application.usecases;

import com.devtsu.bank.BankApplication;
import com.devtsu.bank.domain.entities.Client;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.springframework.test.context.junit4.SpringRunner;
import org.testcontainers.junit.jupiter.Testcontainers;

@SpringBootTest(classes = BankApplication.class)
@AutoConfigureMockMvc
@RunWith(SpringRunner.class)
public class CreateClientUseCaseTest {

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


    @Test
    public void verify_call_client_service_create(){
        createClientUseCase.execute(Client.builder().build());
    }
}

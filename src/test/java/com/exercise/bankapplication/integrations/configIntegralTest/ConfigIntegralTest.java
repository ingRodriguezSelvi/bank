package com.exercise.bankapplication.integrations.configIntegralTest;

import com.exercise.bankapplication.BankApplication;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = {BankApplication.class})
public abstract class ConfigIntegralTest {
    private static final MyPostgresqlContainer postgresSQLContainer = MyPostgresqlContainer.getInstance();

    @DynamicPropertySource
    static void setProperties(DynamicPropertyRegistry registry) {

        registry.add("spring.datasource.url", () -> postgresSQLContainer.getJdbcUrl());
        registry.add("spring.datasource.username", postgresSQLContainer::getUsername);
        registry.add("spring.datasource.password", postgresSQLContainer::getPassword);
        registry.add("spring.flyway.enabled", () -> true);

    }
}

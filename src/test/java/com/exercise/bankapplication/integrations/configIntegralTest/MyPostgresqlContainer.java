package com.exercise.bankapplication.integrations.configIntegralTest;

import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.utility.DockerImageName;

public class MyPostgresqlContainer extends PostgreSQLContainer {
    private static MyPostgresqlContainer container;

    private MyPostgresqlContainer() {
        super(DockerImageName.parse("postgres:11-alpine"));
    }

    public static MyPostgresqlContainer getInstance() {
        if (container == null) {
            container = new MyPostgresqlContainer();
            container.start();
        }
//        container.start();
        return container;
    }


    @Override
    public void start() {
        super.start();
        System.setProperty("DB_HOST", this.getJdbcUrl());
        System.setProperty("DB_PORT", String.valueOf(this.getMappedPort(5432)));
        System.setProperty("DB_NAME", "test");
    }

    @Override
    public void stop() {
        //do nothing, JVM handles shut down
    }
}

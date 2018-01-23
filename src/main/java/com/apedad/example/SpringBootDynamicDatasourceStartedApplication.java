package com.apedad.example;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class})
public class SpringBootDynamicDatasourceStartedApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringBootDynamicDatasourceStartedApplication.class, args);
    }
}

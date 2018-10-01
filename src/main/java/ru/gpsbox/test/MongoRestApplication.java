package ru.gpsbox.test;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;


@SpringBootApplication
@EnableMongoRepositories(basePackages={"ru.gpsbox.test.persistance.mongo"})
public class MongoRestApplication {
    public static void main(String[] args) {
        SpringApplication.run(MongoRestApplication.class, args);
    }
}

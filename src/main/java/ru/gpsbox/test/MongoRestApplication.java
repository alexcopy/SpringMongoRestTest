package ru.gpsbox.test;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;
import org.springframework.scheduling.annotation.EnableScheduling;


@SpringBootApplication
@EnableScheduling
@EnableMongoRepositories(basePackages={"ru.gpsbox.test.persistance.mongo"})
public class MongoRestApplication {
    public static void main(String[] args) {
        SpringApplication.run(MongoRestApplication.class, args);
    }
}

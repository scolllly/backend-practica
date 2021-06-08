package com.backend.practica;

import com.backend.practica.mongo.repository.ClienteMongoRepository;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

@SpringBootApplication
@EnableMongoRepositories(basePackageClasses = ClienteMongoRepository.class)
// @EnableAutoConfiguration(exclude={MongoAutoConfiguration.class})
public class PracticaApplication {

	public static void main(String[] args) {
		SpringApplication.run(PracticaApplication.class, args);
	}

}

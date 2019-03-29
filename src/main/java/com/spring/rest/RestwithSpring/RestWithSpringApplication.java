package com.spring.rest.RestwithSpring;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableJpaRepositories("com.spring.rest.RestwithSpring.repositories")
@EntityScan("com.spring.rest.RestwithSpring.entities")
public class RestWithSpringApplication {

	public static void main(String[] args) {
		SpringApplication.run(RestWithSpringApplication.class, args);

	}

}

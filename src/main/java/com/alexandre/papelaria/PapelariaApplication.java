package com.alexandre.papelaria;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.orm.jpa.HibernateJpaAutoConfiguration;

@SpringBootApplication(exclude = HibernateJpaAutoConfiguration.class)
public class PapelariaApplication {

	public static void main(String[] args) {
		SpringApplication.run(PapelariaApplication.class, args);
	}

}

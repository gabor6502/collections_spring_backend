package com.api.collections;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

// exclude the default auto configurated security, for now
@SpringBootApplication(exclude = {
    org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration.class
})
public class CollectionsApplication {

	public static void main(String[] args) {
		SpringApplication.run(CollectionsApplication.class, args);
	}

}

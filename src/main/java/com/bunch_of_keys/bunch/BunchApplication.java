package com.bunch_of_keys.bunch;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;

@SpringBootApplication
@EntityScan
public class BunchApplication {

	public static void main(String[] args) {
		SpringApplication.run(BunchApplication.class, args);
	}

}

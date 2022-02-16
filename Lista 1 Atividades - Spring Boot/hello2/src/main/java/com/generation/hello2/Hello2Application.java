package com.generation.hello2;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

@SpringBootApplication (exclude = {DataSourceAutoConfiguration.class})
public class Hello2Application {

	public static void main(String[] args) {
		SpringApplication.run(Hello2Application.class, args);
			
	}

}

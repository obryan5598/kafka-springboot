package com.example.kafka.springboot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
public class KafkaSpringbootApplication {

	public static void main(String[] args) {
		SpringApplication.run(KafkaSpringbootApplication.class, args);
	}

}

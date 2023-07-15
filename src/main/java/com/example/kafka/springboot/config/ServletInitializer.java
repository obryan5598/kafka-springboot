package com.example.kafka.springboot.config;

import com.example.kafka.springboot.KafkaSpringbootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

public class ServletInitializer extends SpringBootServletInitializer {

	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
		return application.sources(KafkaSpringbootApplication.class);
	}

}

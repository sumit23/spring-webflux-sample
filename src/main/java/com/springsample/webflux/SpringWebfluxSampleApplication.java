package com.springsample.webflux;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = "com.springsample.webflux")
public class SpringWebfluxSampleApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringWebfluxSampleApplication.class, args);
	}

}

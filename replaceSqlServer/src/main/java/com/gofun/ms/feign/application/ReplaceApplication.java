package com.gofun.ms.feign.application;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;


@SpringBootApplication
@ComponentScan(basePackages = {"com.gofun"})
public class ReplaceApplication {

	public static void main(String[] args) {
		SpringApplication.run(ReplaceApplication.class, args);
	}

}
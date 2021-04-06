package com.example.GemifireRestAPISample;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.gemfire.repository.config.EnableGemfireRepositories;

@EnableGemfireRepositories
@SpringBootApplication
public class GemifireRestApiSampleApplication {

	public static void main(String[] args) {
		SpringApplication.run(GemifireRestApiSampleApplication.class, args);
	}

}

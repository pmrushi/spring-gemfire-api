package com.example.GemifireRestAPISample;

import com.example.GemifireRestAPISample.model.CustomerObj;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.gemfire.config.annotation.EnableCachingDefinedRegions;
import org.springframework.data.gemfire.config.annotation.EnableEntityDefinedRegions;
import org.springframework.data.gemfire.repository.config.EnableGemfireRepositories;

@EnableGemfireRepositories
@SpringBootApplication
@EnableCachingDefinedRegions
@EnableEntityDefinedRegions(basePackageClasses = CustomerObj.class)
public class GemifireRestApiSampleApplication {

	public static void main(String[] args) {
		SpringApplication.run(GemifireRestApiSampleApplication.class, args);
	}

}

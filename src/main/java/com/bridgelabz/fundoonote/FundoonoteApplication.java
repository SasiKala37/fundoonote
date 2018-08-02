package com.bridgelabz.fundoonote;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.elasticsearch.repository.config.EnableElasticsearchRepositories;

@SpringBootApplication

public class FundoonoteApplication {

	public static void main(String[] args) {
		SpringApplication.run(FundoonoteApplication.class, args);
	}
}

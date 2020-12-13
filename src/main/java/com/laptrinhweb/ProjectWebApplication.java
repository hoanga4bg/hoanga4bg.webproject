package com.laptrinhweb;

import javax.annotation.sql.DataSourceDefinition;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication

public class ProjectWebApplication {

	public static void main(String[] args) {
		SpringApplication.run(ProjectWebApplication.class, args);
	}

}

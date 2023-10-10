package com.karatesan.RESTAPI.datasource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.karatesan.RESTAPI.model.Employee;
import com.karatesan.RESTAPI.repositories.EmployeeRepository;

@Configuration
public class LoadDatabase {
	
	private static final Logger log = LoggerFactory.getLogger(LoadDatabase.class);
	
	@Bean
	CommandLineRunner initDatabase(EmployeeRepository repository) {
		return args->{
			log.info("Preloading " + repository.save(new Employee("Zbój", "Bandyta")));
			log.info("Preloading " + repository.save(new Employee("Wój", "Bijacz")));
		};
	}
	

}

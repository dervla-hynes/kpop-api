package com.dervla.kpopapi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

@SpringBootApplication(exclude={DataSourceAutoConfiguration.class})
public class KpopapiApplication {

	public static void main(String[] args) {
		SpringApplication.run(KpopapiApplication.class, args);
	}

}

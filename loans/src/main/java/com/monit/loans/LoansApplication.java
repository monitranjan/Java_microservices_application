package com.monit.loans;

import com.monit.loans.dto.LoansInfoDto;
import io.swagger.v3.oas.annotations.ExternalDocumentation;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.info.License;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing(auditorAwareRef = "auditAwareImpl")
@EnableConfigurationProperties(value = {LoansInfoDto.class})
@OpenAPIDefinition(info = @Info(
		title = "Microservices REST API Documentation",
		description = "Loans Microservices REST API Documentation",
		version = "v1",
		contact = @Contact(
				name = "Monit Ranjan",
				email = "monitranjan2050@gmail.com",
				url = "https://www.linkedin.com/monit-kumar-ranjan"
		),
		license = @License(
				name = "Apache 2.0",
				url = "https://www.linkedin.com/monit-kumar-ranjan"
		)),
		externalDocs = @ExternalDocumentation(
				description = "Loans Microservices REST API Documentation",
				url = "https://www.linkedin.com/monit-kumar-ranjan"
		)
)
public class LoansApplication {

	public static void main(String[] args) {
		SpringApplication.run(LoansApplication.class, args);
	}

}

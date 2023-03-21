package app.it_academy.fitnessAppAudit;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class FitnessAppAuditApplication {

	public static void main(String[] args) {
		SpringApplication.run(FitnessAppAuditApplication.class, args);
	}

}

package app.it_academy.fitnessAppUsers;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class FitnessAppUsersApplication {

	public static void main(String[] args) {
		SpringApplication.run(FitnessAppUsersApplication.class, args);
	}

}

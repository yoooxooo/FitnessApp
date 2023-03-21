package app.it_academy.fitnessAppProducts;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class FitnessAppProductsApplication {

	public static void main(String[] args) {
		SpringApplication.run(FitnessAppProductsApplication.class, args);
	}

}

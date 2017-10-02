package eu.beersafe.mooc;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan({"eu.beersafe.mooc", "eu.beersafe.mooc.controllers", "eu.beersafe.mooc.data"})
public class BeerSafeApplication {

	public static void main(String[] args) {
		SpringApplication.run(BeerSafeApplication.class, args);
	}
}

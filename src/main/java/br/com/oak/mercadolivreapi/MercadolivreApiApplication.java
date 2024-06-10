package br.com.oak.mercadolivreapi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;

@SpringBootApplication(exclude = { SecurityAutoConfiguration.class })
public class MercadolivreApiApplication {

	public static void main(String[] args) {
		SpringApplication.run(MercadolivreApiApplication.class, args);
	}

}

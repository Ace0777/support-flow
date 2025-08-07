package br.com.ace.supportflowbff;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@EnableFeignClients
@SpringBootApplication
public class SupportflowBffApplication {

	public static void main(String[] args) {
		SpringApplication.run(SupportflowBffApplication.class, args);
	}

}

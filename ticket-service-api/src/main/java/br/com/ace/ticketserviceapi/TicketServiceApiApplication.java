package br.com.ace.ticketserviceapi;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;


@EnableFeignClients
@SpringBootApplication
public class TicketServiceApiApplication  {


	public static void main(String[] args) {
		SpringApplication.run(TicketServiceApiApplication.class, args);
	}


}

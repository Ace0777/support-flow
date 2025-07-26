package br.com.ace.queueserviceapi;

import br.com.ace.queueserviceapi.entities.Queue;
import br.com.ace.queueserviceapi.repositories.QueueRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@RequiredArgsConstructor
public class QueueServiceApiApplication implements CommandLineRunner {

	private final QueueRepository repository;

	public static void main(String[] args) {
		SpringApplication.run(QueueServiceApiApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		repository.save(
				Queue.builder()
						.requesterId("1")
						.customerId("2")
						.title("Test Queue")
						.description("This is a test queue for the Queue Service API.")
						.build()
		);
	}
}

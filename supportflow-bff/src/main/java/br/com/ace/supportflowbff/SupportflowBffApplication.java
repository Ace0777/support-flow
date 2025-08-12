package br.com.ace.supportflowbff;

import org.hibernate.Hibernate;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.autoconfigure.orm.jpa.HibernateJpaAutoConfiguration;
import org.springframework.cloud.openfeign.EnableFeignClients;

@EnableFeignClients
@SpringBootApplication(
		exclude = {
			DataSourceAutoConfiguration.class,
			HibernateJpaAutoConfiguration.class
		}
)
public class SupportflowBffApplication {

	public static void main(String[] args) {
		SpringApplication.run(SupportflowBffApplication.class, args);
	}

}

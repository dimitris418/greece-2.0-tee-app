package gr.aueb.cf.teeapp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class TeeappApplication {

	public static void main(String[] args) {
		SpringApplication.run(TeeappApplication.class, args);
	}
}

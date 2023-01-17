package pl.bobak.integrations.fdademo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

@SpringBootApplication
@EnableConfigurationProperties
public class FdademoApplication {

	public static void main(String[] args) {
		SpringApplication.run(FdademoApplication.class, args);
	}

}

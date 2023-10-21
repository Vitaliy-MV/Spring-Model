package arch.example.config;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.UserDetailsServiceAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;

@ComponentScan("arch.example")
@SpringBootApplication(exclude={UserDetailsServiceAutoConfiguration.class})
public class BootApplication{
	public static void main(String[] args) {
		SpringApplication.run(BootApplication.class, args);
	}
}
package books;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;

@ComponentScan // Automatically discover other bean components, configurations and services in the current classpath
@EnableAutoConfiguration // Enable Spring Boot Auto Configuration - this will enable Spring MVC as this is in our classpath 

public class Application
{
	public static void main(String[] args)
	{
		SpringApplication.run(Application.class, args);
	}
}
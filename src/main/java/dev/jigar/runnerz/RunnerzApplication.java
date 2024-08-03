package dev.jigar.runnerz;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = "dev.jigar.runnerz")
public class RunnerzApplication {

	private static final Logger log = LoggerFactory.getLogger(RunnerzApplication.class);

	public static void main(String[] args) {
		SpringApplication.run(RunnerzApplication.class, args);
		log.info("Application started successfully");
	}

	// @Bean
	// CommandLineRunner runner(RunRepository runRepository) { // this will run after application context is built
	// 	// CommandLineRunner is an interface that defines a single abstract method 
	// 	// `FunctionalInterface` is an interface that contains only the abstract method. purpose of `FunctionalInterface` is to enable the use of lambda expression and method ref
	// 	return args -> {
	// 		Run run = new Run(1, "jimis burger", LocalDateTime.now(), LocalDateTime.now().plus(1, ChronoUnit.HOURS), 1, Location.INDOOR);
	// 		log.info("run : "+run);
	// 		runRepository.create(run);
	// 	};
	// }

}

package dev.jigar.runnerz;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jdbc.repository.config.EnableJdbcRepositories;
import org.springframework.web.client.RestClient;
import org.springframework.web.client.support.RestClientAdapter;
import org.springframework.web.service.invoker.HttpServiceProxyFactory;
import org.springframework.boot.CommandLineRunner;

import dev.jigar.runnerz.run.Location;
import dev.jigar.runnerz.run.Run;
import dev.jigar.runnerz.user.User;
import dev.jigar.runnerz.user.UserHttpClient;
import dev.jigar.runnerz.user.UserRestClient;



@SpringBootApplication
public class RunnerzApplication {

	private static final Logger log = LoggerFactory.getLogger(RunnerzApplication.class);

	public static void main(String[] args) {
		SpringApplication.run(RunnerzApplication.class, args);
		log.info("Application started successfully");
	}

	@Bean
	UserHttpClient userHttpClient() {
		RestClient restClient = RestClient.create("https://jsonplaceholder.typicode.com/");
		HttpServiceProxyFactory factory = HttpServiceProxyFactory.builderFor(RestClientAdapter.create(restClient)).build();
		return factory.createClient(UserHttpClient.class);
	}
	

	@Bean
	CommandLineRunner runner(UserHttpClient client) { // this will run after application context is built
		// CommandLineRunner is an interface that defines a single abstract method 
		// `FunctionalInterface` is an interface that contains only the abstract method. purpose of `FunctionalInterface` is to enable the use of lambda expression and method ref
		return args -> {
			List<User> users = client.findAll();
			System.out.println(users);

			User user = client.findById(1);
			System.out.println(user);
		};
	}

}

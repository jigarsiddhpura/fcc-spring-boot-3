package dev.jigar.runnerz;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import foo.bar.Welcome;

@SpringBootApplication
public class RunnerzApplication {

	public static void main(String[] args) {
		SpringApplication.run(RunnerzApplication.class, args);

		var welcomemsg = new Welcome();
		System.out.println(welcomemsg.getWelcomeMsg());
	}

}

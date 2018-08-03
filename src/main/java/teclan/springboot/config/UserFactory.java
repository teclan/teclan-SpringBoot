package teclan.springboot.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import teclan.springboot.model.User;

import org.springframework.beans.factory.annotation.Value;

@Configuration
public class UserFactory {

	private @Value("${user.id}") String id;
	private @Value("${user.post}") String boss;
	private @Value("${user.age}") int age;

	@Bean
	public User getUser() {
		return new User(id, boss, age);
	}

}

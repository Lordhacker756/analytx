package com.umbrella.projectumbrella;

import com.umbrella.projectumbrella.dto.Role;
import com.umbrella.projectumbrella.entities.User;
import com.umbrella.projectumbrella.repositories.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@SpringBootApplication
public class ProjectUmbrellaApplication implements CommandLineRunner {

	private final UserRepository userRepository;

	public ProjectUmbrellaApplication(UserRepository userRepository) {
		this.userRepository = userRepository;
	}

	public static void main(String[] args) {
		SpringApplication.run(ProjectUmbrellaApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		User adminAccount = userRepository.findByRole(Role.ADMIN);

		if(adminAccount == null) {
			User user = new User();

			user.setEmail("admin@gmail.com");
			user.setPassword(new BCryptPasswordEncoder().encode("admin"));
			user.setRole(Role.ADMIN);

			userRepository.save(user);

		}
	}
}

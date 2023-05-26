package com.example.hospitalManagement.HMS;

import com.example.hospitalManagement.HMS.Domain.user.Role;
import com.example.hospitalManagement.HMS.Domain.user.User;
import com.example.hospitalManagement.HMS.auth.AuthenticationService;
import com.example.hospitalManagement.HMS.auth.RegisterRequest;
import com.example.hospitalManagement.HMS.config.JwtService;
import com.example.hospitalManagement.HMS.repository.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.password.PasswordEncoder;

@SpringBootApplication
public class HospitalManagementSystemApplication {

	public HospitalManagementSystemApplication(UserRepository userRepository, JwtService jwtService, PasswordEncoder passwordEncoder) {
		this.userRepository = userRepository;
		this.passwordEncoder = passwordEncoder;
	}

	public static void main(String[] args) {
		SpringApplication.run(HospitalManagementSystemApplication.class, args);
	}
	private final UserRepository userRepository;
	private final PasswordEncoder passwordEncoder;

	@Bean
	public CommandLineRunner commandLineRunner(AuthenticationService authenticationService, UserRepository userRepository) {
		return args -> {
			var adminRequest  = RegisterRequest.builder()
					.username("admin")
					.firstName("Ismail")
					.lastName("AlAmir")
					.location("Amman")
					.phone("1234567890")
					.email("admin@example.com")
					.password(passwordEncoder.encode("password"))
					.role(Role.ADMIN)
					.build();


			User admin = adminRequest.toUser();

			System.out.println(admin);

			// Save the admin user to the database
			userRepository.save(admin);
		};
	}

}

package com.loginAndRoles;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.loginAndRoles.model.User;
import com.loginAndRoles.repositories.UserRepository;

@SpringBootTest
class LoginRolesDbApplicationTests {
	
	@Autowired
	private UserRepository userRepository;

	@Test
	void contextLoads() {
	}

	@Test
	public void createUser() {
		User user = new User();
		user.setName("Paul");
		user.setEmail("paul@gmail.com");
		
		User savedUser = userRepository.save(user);
		
		User newUser = userRepository.findById(savedUser.getId()).get();
		assertEquals("Paul", newUser.getName());
		assertEquals("paul@gmail.com", newUser.getEmail());
	}
}

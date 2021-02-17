package com.loginAndRoles.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.loginAndRoles.model.User;

@Repository("userRepository")
public interface UserRepository extends JpaRepository<User, Integer> {
	
	public User findByName(String name);

	public User findByEmail(String email);

	public User findByConfirmationToken(String confirmationToken);

}
package com.loginAndRoles.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.loginAndRoles.model.CurrentUserDetails;
import com.loginAndRoles.model.User;
import com.loginAndRoles.repositories.UserRepository;

/**
 * Spring Security usara esta clase para autenticar a los usuarios, por eso
 * implementamos UserDetailsService
 *
 */
@Service
public class UserService implements UserDetailsService {

	@Autowired
	private UserRepository userRepository;

	public User findByEmail(String email) {
		return userRepository.findByEmail(email);
	}
	
	public User findByConfirmationToken(String confirmationToken) {
		return userRepository.findByConfirmationToken(confirmationToken);
	}

	public void saveUser(User entity) {
		userRepository.save(entity);
	}
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		User user = userRepository.findByName(username);
		if (user == null) {
			throw new UsernameNotFoundException("No se encuentra ese usuario");
		}
		
		return new CurrentUserDetails(user);
	}
}
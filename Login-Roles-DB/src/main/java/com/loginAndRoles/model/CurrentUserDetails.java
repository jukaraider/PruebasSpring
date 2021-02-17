package com.loginAndRoles.model;

import java.util.Collection;
import java.util.HashSet;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

/**
 * Spring Security no puede manejar objetos User directamente, por eso se le
 * crea este Wrapper
 * 
 */
public class CurrentUserDetails implements UserDetails {

	private static final long serialVersionUID = -4486381137172695904L;
	private User user;

	public CurrentUserDetails(User user) {
		this.user = user;
	}

	@Override
	public String getUsername() {
		return user.getName();
	}

	@Override
	public String getPassword() {
		return user.getPassword();
	}

	@Override
	public boolean isEnabled() {
		return user.getEnabled();
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return new HashSet<GrantedAuthority>();
	}

	@Override
	public boolean isAccountNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}
}
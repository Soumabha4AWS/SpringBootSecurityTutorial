package com.springboot.security.nojwt.demo.security.object;

import java.util.Collection;
import java.util.Collections;
import java.util.Set;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.springboot.security.nojwt.demo.entity.AppUsers;

public class UserDetailsImpl implements UserDetails {
	
	private AppUsers appUsers;
	
	

	public UserDetailsImpl(AppUsers appUsers) {
		super();
		this.appUsers = appUsers;
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {	
		System.out.println("Collection returned is "+Collections.singleton(new SimpleGrantedAuthority("USER")));
		// return Collections.singleton(new SimpleGrantedAuthority("USER"));
		System.out.println("Collection getAuthorities returned is "+Set.of(new SimpleGrantedAuthority("USER")).stream().findAny().orElse(null).getAuthority());
		return Set.of(new SimpleGrantedAuthority("USER"));
	}

	@Override
	public String getPassword() {		
		return appUsers.getPassword();
	}

	@Override
	public String getUsername() {		
		return appUsers.getUserName();
	}

	@Override
	public boolean isAccountNonExpired() {		
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	@Override
	public boolean isEnabled() {
		return true;
	}

}

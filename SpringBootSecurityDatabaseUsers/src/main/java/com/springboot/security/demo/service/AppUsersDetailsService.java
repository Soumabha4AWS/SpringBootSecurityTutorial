package com.springboot.security.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.springboot.security.demo.entity.AppUsers;
import com.springboot.security.demo.repository.AppUsersDetailsRepository;
import com.springboot.security.demo.security.object.UserDetailsImpl;


@Service
public class AppUsersDetailsService implements UserDetailsService {

	@Autowired
	private AppUsersDetailsRepository appUsersDetailsRepository;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {		
		AppUsers appUsers = appUsersDetailsRepository.findByUserName(username);
		System.out.println("appUsers are "+appUsers.toString());
		if (appUsers == null) throw new UsernameNotFoundException("User Not Found");		
		return new UserDetailsImpl(appUsers);
	}

}

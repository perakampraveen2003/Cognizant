package com.admin.service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.admin.entity.AdminDetails;
import com.admin.repository.AdminRepository;

@Service
public class JwtUserDetailsService implements UserDetailsService {

	@Autowired
	private AdminRepository repo;
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		AdminDetails admin = repo.findByUsername(username);
		if (admin.getUserName().equals(username)) {
			Set<SimpleGrantedAuthority> authorities = new HashSet<SimpleGrantedAuthority>();
			authorities.add(new SimpleGrantedAuthority("ROLE_"+"ADMIN"));
			return new User(admin.getUserName(), admin.getPassword(),
					authorities);
		} else {
			throw new UsernameNotFoundException("User not found with username: " + username);
		}
	}
}
package com.example.usermanagement.security;

import java.util.stream.Collectors;

import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.*;
import org.springframework.stereotype.Service;

import com.example.usermanagement.repository.UserRepository;

@Service
public class CustomUserDetailsService implements UserDetailsService{
	 private final UserRepository userRepo;

	    public CustomUserDetailsService(UserRepository userRepo) {
	        this.userRepo = userRepo;
	    }

	    @Override
	    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
	        var user = userRepo.findByEmail(email)
	                .orElseThrow(() -> new UsernameNotFoundException("User not found: " + email));
	        var authorities = user.getRoles()
	                .stream()
	                .map(r -> new SimpleGrantedAuthority("ROLE_" + r.getName()))
	                .collect(Collectors.toSet());
	        return new org.springframework.security.core.userdetails.User(
	                user.getEmail(), user.getPassword(), authorities);
	    }
}

package com.edix.cookbook.services.impl;

import java.util.Collections;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.edix.cookbook.models.Usuario;
import com.edix.cookbook.repository.UsuarioRepository;

@Service
public class AppUserDetailService  implements UserDetailsService{

	    @Autowired
	    private UsuarioRepository userRepository;

	    @Override
	    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {

	        Usuario user = userRepository.findByEmail(email);
	        if (user == null) {
	            throw new UsernameNotFoundException("User '" + email + "' not found");
	        }

	        return org.springframework.security.core.userdetails.User
	                .withUsername(user.getEmail())
	                .password(user.getPassword())
	                .authorities(Collections.emptyList())
	                .accountExpired(false)
	                .accountLocked(false)
	                .credentialsExpired(false)
	                .disabled(false)
	                .build();

	    }
	    
	    
	
}

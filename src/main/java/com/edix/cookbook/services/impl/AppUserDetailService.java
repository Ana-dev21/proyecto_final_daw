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
	    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

	        Usuario user = userRepository.findByUsername(username);
	        if (user == null) {
	            throw new UsernameNotFoundException("User '" + username + "' not found");
	        }

	        return org.springframework.security.core.userdetails.User
	                .withUsername(username)
	                .password(user.getPassword())
	                .authorities(Collections.emptyList())
	                .accountExpired(false)
	                .accountLocked(false)
	                .credentialsExpired(false)
	                .disabled(false)
	                .build();

	    }
	    
	    
	
}

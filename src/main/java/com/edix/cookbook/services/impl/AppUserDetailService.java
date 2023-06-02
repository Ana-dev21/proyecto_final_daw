package com.edix.cookbook.services.impl;

import java.util.List;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.edix.cookbook.models.Usuario;
import com.edix.cookbook.repository.UsuarioRepository;

/**
 * implementa la interfaz UserDetailsService de Spring Security y cargar los detalles del usuario 
 * durante el proceso de autenticación. Sobrescribe el método loadUserByUsername 
 * consultando a la base de datos para obtener el usuario correspondiente al email proporcionado. 
 * A partir de los datos del usuario, se crea y devuelve un objeto UserDetails de Spring Security 
 * que contiene el email, la contraseña y los roles del usuario.
 *
 */
@Service
public class AppUserDetailService  implements UserDetailsService{

	    @Autowired
	    private UsuarioRepository userRepository;

	    /**
		 * Carga los detalles de un usuario por su nombre de usuario (email).
		 *
		 * @param email El nombre de usuario (email) del usuario a cargar.
		 * @return UserDetails que representa los detalles del usuario.
		 * @throws UsernameNotFoundException Si no se encuentra el usuario.
	     */
	    @Override
	    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {

	        Usuario user = userRepository.findByEmail(email);
	        if (user == null) {
	            throw new UsernameNotFoundException("User '" + email + "' not found");
	        }
	        List<GrantedAuthority> roles = user.getUsuarioConRoles().stream()
	        		.map(uc -> new SimpleGrantedAuthority("ROLE_"+uc.getRole().getNombreRol())).collect(Collectors.toList());
	        
	        return org.springframework.security.core.userdetails.User
	                .withUsername(user.getEmail())
	                .password(user.getPassword())
	                .authorities(roles)
	                .accountExpired(false)
	                .accountLocked(false)
	                .credentialsExpired(false)
	                .disabled(false)
	                .build();

	    }
	    
	    
	
}

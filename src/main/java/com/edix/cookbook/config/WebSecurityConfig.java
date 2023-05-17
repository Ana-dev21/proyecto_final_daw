package com.edix.cookbook.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.edix.cookbook.services.impl.AppUserDetailService;

@EnableWebSecurity
@Configuration
class WebSecurityConfig extends WebSecurityConfigurerAdapter {

	
	 @Autowired
	    private AppUserDetailService userDetailService;
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable().authorizeRequests()
        .antMatchers(HttpMethod.POST, "/usuario/login").permitAll()
        .antMatchers(HttpMethod.POST, "/usuario/encrypt").permitAll()
        .antMatchers(HttpMethod.POST, "/usuario/register").permitAll()
        .anyRequest().authenticated()
        .and()
        .addFilterBefore(new JWTLoginFilter("/usuario/login", authenticationManager()),
                UsernamePasswordAuthenticationFilter.class)
        // And filter other requests to check the presence of JWT in header
        .addFilterBefore(new JWTAuthenticationFilter(),
                UsernamePasswordAuthenticationFilter.class);
	}
	
	
	@Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
      
        auth.userDetailsService(userDetailService).passwordEncoder(passwordEncoder());
        
    }
	
	@Bean
    PasswordEncoder passwordEncoder(){
    
        return new BCryptPasswordEncoder(12);
        
    }
	
}

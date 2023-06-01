package com.edix.cookbook.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
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
	 
	 
	 private static final String[] WHITELIST_MAIN_POST = {

			 "/usuario/login",
			 "/usuario/encrypt",
			 "/usuario/register"
	    };
	 
	 private static final String[] WHITELIST_MAIN_GET = {

			 "/planes/**",
			 "/uploads/**",
			 "/categorias/**",
			 "/maestros/**",
			 "/usuarios/**",
			 "/usuario/**",
			 "/recetas/**"
	    };
	 
	 private static final String[] WHITELIST_SWAGGER = {

	            // for Swagger UI v2
	            "/v2/api-docs",
	            "/swagger-ui.html",
	            "/swagger-resources",
	            "/swagger-resources/**",
	            "/configuration/ui",
	            "/configuration/security",
	            "/webjars/**",

	            // for Swagger UI v3 (OpenAPI)
	            "/v3/api-docs/**",
	            "/swagger-ui/**"
	    }; 
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
        http.cors().and().csrf().disable().authorizeRequests()
        .antMatchers(HttpMethod.POST,WHITELIST_MAIN_POST).permitAll()
        .antMatchers(HttpMethod.GET,WHITELIST_MAIN_GET).permitAll()
        .antMatchers(WHITELIST_SWAGGER).permitAll()
        .antMatchers("/usuario/todos").hasRole("admin")
        .antMatchers().hasRole("admin")
        
        
        .anyRequest().authenticated()
        .and()
        .addFilterBefore(new JWTAuthenticationFilter(),
                UsernamePasswordAuthenticationFilter.class);
	}
	
	@Bean
    protected AuthenticationManager getAuthenticationManager() throws Exception {
        return authenticationManager();
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

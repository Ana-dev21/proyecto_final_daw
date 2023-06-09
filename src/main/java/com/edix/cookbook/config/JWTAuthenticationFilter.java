package com.edix.cookbook.config;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.GenericFilterBean;
import org.springframework.web.filter.OncePerRequestFilter;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.UnsupportedJwtException;

public class JWTAuthenticationFilter extends GenericFilterBean {

	   @Override
	    public void doFilter(ServletRequest request,
	            ServletResponse response,
	            FilterChain filterChain)
	            throws IOException, ServletException {
	        Authentication authentication = TokenAuthenticationService
	                .getAuthentication((HttpServletRequest) request);

	        SecurityContextHolder.getContext()
	                .setAuthentication(authentication);
	        filterChain.doFilter(request, response);
	    }

}

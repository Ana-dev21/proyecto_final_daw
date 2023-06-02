package com.edix.cookbook.config;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import static java.util.Collections.emptyList;

import java.io.IOException;

public class TokenAuthenticationService {
	static final long EXPIRATIONTIME = 864_000_000; // 10 days
	static final String SECRET = "03AC6742122F3E15C761EE1A5E255F067953623C8B388B4459E13F978D7C846F4";
	static final String TOKEN_PREFIX = "Bearer";
	static final String HEADER_STRING = "Authorization";
	//Para poder acceder al header Authorization por código
	static final String HEADER_ACCESS_CONTROL = "Access-Control-Expose-Headers";
	
	
	 public static void addAuthentication(HttpServletResponse res, String email, List<GrantedAuthority> roles ) throws IOException {
		
		 String subject = email + "#"+ String.join("#", roles.stream().map( rol -> rol.getAuthority()).toList());
		 String JWT = Jwts.builder().setSubject(subject)
				.setExpiration(new Date(System.currentTimeMillis() + EXPIRATIONTIME))
                .signWith(SignatureAlgorithm.HS256, SECRET).compact();
		res.addHeader(HEADER_STRING, TOKEN_PREFIX + " " + JWT);
		res.addHeader(HEADER_ACCESS_CONTROL, HEADER_STRING);
	}

	 static Authentication getAuthentication(HttpServletRequest request) {
		String token = request.getHeader(HEADER_STRING);
		if (token != null) {
			// parse the token.
			String subject = Jwts.parser().setSigningKey(SECRET).parseClaimsJws(token.replace(TOKEN_PREFIX, "")).getBody()
					.getSubject();
			
			 String[] splitedSubject = subject.split("#");
			 String user = splitedSubject [0];
			 
			 List<GrantedAuthority> roles = new ArrayList<>();
			 
			 for(int cont=1; cont < splitedSubject.length ; cont++) {
				 
				 roles.add(new SimpleGrantedAuthority("ROLE_"+splitedSubject[cont]) );
			 }
			 
			return user != null ? new UsernamePasswordAuthenticationToken(user, null, roles) : null;
		}
		return null;
	}
}

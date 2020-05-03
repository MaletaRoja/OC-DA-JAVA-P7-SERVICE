package com.formation.projet7.security;

import java.io.IOException;
import java.util.Date;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.formation.projet7.constants.Constants;
import com.formation.projet7.model.Utilisateur;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

public class JWTAuthenticationFilter extends UsernamePasswordAuthenticationFilter {

	private AuthenticationManager authenticationManager;

	public JWTAuthenticationFilter(AuthenticationManager authenticationManager) {
		super();
		this.authenticationManager = authenticationManager;
	}

	public JWTAuthenticationFilter() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response)
			throws AuthenticationException {

		Utilisateur user = null;
		try {

			user = new ObjectMapper().readValue(request.getInputStream(), Utilisateur.class);

		} catch (Exception e) {
			throw new RuntimeException(e);
		}

		return authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(

				user.getUsername(), user.getPassword()));

	}

	@Override
	protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain,
			Authentication authResult) throws IOException, ServletException {
		
		User springUser =(User) authResult .getPrincipal();
		String jwtToken = Jwts.builder()
		.setSubject(springUser.getUsername())
		.setExpiration (new Date(System.currentTimeMillis()+ Constants.EXPIRATION_TIME))
		.signWith (SignatureAlgorithm.HS256, Constants.YOUR_SECRET)
		.claim("roles", springUser.getAuthorities())
		.compact();
		response.addHeader(Constants.AUTHORIZATION_HEADER ,
		Constants.BEARER_TOKEN + jwtToken);
		}
		
	}



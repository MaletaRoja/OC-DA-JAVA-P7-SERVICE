package com.formation.projet7.security;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.catalina.authenticator.SpnegoAuthenticator.AuthenticateAction;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AbstractAuthenticationProcessingFilter;
import org.springframework.security.web.util.matcher.RequestMatcher;
import com.formation.projet7.constants.Constants;
import com.formation.projet7.model.JWTAuthenticationToken;

public class JWTAuthenticationFilter extends AbstractAuthenticationProcessingFilter {

	
/*
	protected JWTAuthenticationFilter(RequestMatcher requiresAuthenticationRequestMatcher) {
		super("/biblio/ouvrage/**");
		
		
	}
*/
	public JWTAuthenticationFilter() {
		super("/aaaa/**");  // Solution fonctionnelle sans sécurité
		//super("/biblio/ouvrage/**");
	}

	@Override
	public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response)
			throws AuthenticationException, IOException, ServletException {
		
		String header = request.getHeader(Constants.AUTHORIZATION_HEADER);
		
		if (header == null || header.startsWith(Constants.BEARER_TOKEN)) {
			
			throw new RuntimeException("JWT incorrect");
		}
		
		String authenticationToken = header.substring(7);
		JWTAuthenticationToken token = new JWTAuthenticationToken(authenticationToken);
		
		return getAuthenticationManager().authenticate(token);
	}
	
	@Override
	protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain,
			Authentication authResult) throws IOException, ServletException {
		
		super.successfulAuthentication(request, response, chain, authResult);
		chain.doFilter(request, response);
	}

}

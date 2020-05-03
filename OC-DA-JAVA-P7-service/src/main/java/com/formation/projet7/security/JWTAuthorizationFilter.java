package com.formation.projet7.security;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Map;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.ReportAsSingleViolation;

import org.apache.tomcat.util.bcel.classfile.Constant;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import com.formation.projet7.constants.Constants;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import net.bytebuddy.asm.MemberSubstitution.Substitution.Chain;

public class JWTAuthorizationFilter extends OncePerRequestFilter {

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		
		response.addHeader("Access-Control-Allow-Origin", "*");
		response.addHeader("Access-Control-Allow-Headers", "Origin, Accept, X-Request-With, Headers, authorization");
		response.addHeader("Access-Control-Expose-Headers", "Access-Control-Allow-Origin,Access-Control-Allow-Credentials, authorization");
		String jwtToken=request.getHeader(Constants.AUTHORIZATION_HEADER);
		if (request.getMethod().equals("OPTIONS")) {
			
			response.setStatus(HttpServletResponse.SC_OK);
		}else
			
		if (jwtToken == null || !jwtToken.startsWith(Constants.BEARER_TOKEN)) {
			
			filterChain.doFilter(request, response); return;
		}
		
		Claims claims =Jwts.parser()
				.setSigningKey(Constants.YOUR_SECRET)
				.parseClaimsJws(jwtToken.replace(Constants.AUTHORIZATION_HEADER, ""))
				.getBody();
		
		String username = claims.getSubject();
		
		ArrayList<Map<String, String>> roles = (ArrayList<Map<String,String>>) claims.get("roles");
		Collection<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();
		roles.forEach(r->{
			authorities.add(new SimpleGrantedAuthority(r.get(authorities)));
	});
	UsernamePasswordAuthenticationToken authenticatedUser = 
			new UsernamePasswordAuthenticationToken (username, null, authorities);
	SecurityContextHolder.getContext().setAuthentication(authenticatedUser);
	filterChain.doFilter(request , response);
	}
	
		

}

package com.formation.projet7.config;

import java.util.Collections;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.ProviderManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.stereotype.Component;

import com.formation.projet7.model.JWTUserDetails;
import com.formation.projet7.security.JWTAuthenticationEntryPoint;
import com.formation.projet7.security.JWTAuthenticationFilter;
import com.formation.projet7.security.JWTAuthenticationProvider;
import com.formation.projet7.security.JWTSuccesHandler;

@EnableGlobalMethodSecurity(prePostEnabled = true)
@EnableWebSecurity
@Component
@Configuration
public class JWTSecurityConfig extends WebSecurityConfigurerAdapter {

	
	@Autowired 
	PasswordEncoder passwordEncoder;
	
	@Autowired
	private JWTAuthenticationProvider authenticationProvider;

	@Autowired
	private JWTAuthenticationEntryPoint entryPoint;

	@Bean
	public AuthenticationManager authenticationManager() {

		return new ProviderManager(Collections.singletonList(authenticationProvider));
	}

	@Bean
	public JWTAuthenticationFilter authenticationTokenFilter() {

		JWTAuthenticationFilter filter = new JWTAuthenticationFilter();
		filter.setAuthenticationManager(authenticationManager());
		filter.setAuthenticationSuccessHandler(new JWTSuccesHandler());
		return filter;
	}
	
	//////////////////////////////////////////////////////////
	
	///        Configuration password encoder
	
	//////////////////////////////////////////////////////////
	/*
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		
		 auth.userDetailsService(jwtUserDelails).passwordEncoder(passwordEncoder);
	}
	*/
	
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {

		http.csrf().disable().authorizeRequests().antMatchers("/biblio/connexion/" // solution fonctionnelle 1

		) // toujours autorisée
				.permitAll().antMatchers("/biblio/access" // solution fonctionnelle 1
						, "/biblio/ouvrage/**") // authentification requise

				.authenticated().and().exceptionHandling().authenticationEntryPoint(entryPoint).and()
				.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);

		http.addFilterBefore(authenticationTokenFilter(), UsernamePasswordAuthenticationFilter.class);
		http.headers().cacheControl();
	}

	@Bean
	public PasswordEncoder passwordEncoder() {

		return new BCryptPasswordEncoder();
	}

	
	 
}

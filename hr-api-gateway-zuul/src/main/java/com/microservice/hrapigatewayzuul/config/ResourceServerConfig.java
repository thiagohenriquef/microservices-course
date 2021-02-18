package com.microservice.hrapigatewayzuul.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configurers.ResourceServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.token.store.JwtTokenStore;

@Configuration
@EnableResourceServer
public class ResourceServerConfig extends ResourceServerConfigurerAdapter {
	private final static String PROFILE_ADMIN = "ADMIN";
	private final static String PROFILE_OPERATOR = "OPERATOR";
	
	private final static String[] PUBLIC = { "/hr-oauth/oauth/token" };
	private final static String[] OPERATOR = { "/hr-worker/**" };
	private final static String[] ADMIN = { "/hr-payroll/**", "/hr-user/**", "/actuator/**", "\"/hr-worker/actuator/**",
			"/hr-oauth/actuator/**" };
	
	@Autowired
	private JwtTokenStore tokenStore;

	@Override
	public void configure(ResourceServerSecurityConfigurer resources) throws Exception {
		resources.tokenStore(tokenStore);
	}

	@Override
	public void configure(HttpSecurity http) throws Exception {
		http
			.authorizeRequests()
			.antMatchers(PUBLIC)
			.permitAll()
			.antMatchers(HttpMethod.GET, OPERATOR)
			.hasAnyRole(PROFILE_OPERATOR, PROFILE_ADMIN)
			.antMatchers(ADMIN)
			.hasRole(PROFILE_ADMIN)
			.anyRequest()
			.authenticated();
	}

}

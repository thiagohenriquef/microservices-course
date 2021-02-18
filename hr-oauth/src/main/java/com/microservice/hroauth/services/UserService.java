package com.microservice.hroauth.services;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.microservice.hroauth.entities.User;
import com.microservice.hroauth.feignclients.UserFeignClients;

@Service
public class UserService implements UserDetailsService {
	
	private static Logger logger = LoggerFactory.getLogger(UserService.class);

	@Autowired
	private UserFeignClients userFeignClient;

	public User findbyEmail(String email) {
		User user = userFeignClient.findByEmail(email).getBody();
		if (user == null) {
			logger.error("E-mail not found: " + email);
			throw new IllegalArgumentException("E-mail não encontrado");
		}
		logger.info("Found email: " + email);
		return user;
	}

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		User user = userFeignClient.findByEmail(username).getBody();
		if (user == null) {
			logger.error("E-mail not found: " + username);
			throw new UsernameNotFoundException("E-mail não encontrado");
		}
		logger.info("Found email: " + username);
		return user;
	}
}

package com.microservice.hroauth.services;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.microservice.hroauth.entities.User;
import com.microservice.hroauth.feignclients.UserFeignClients;

@Service
public class UserService {
	
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
}

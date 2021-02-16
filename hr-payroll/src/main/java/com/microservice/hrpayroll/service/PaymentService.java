package com.microservice.hrpayroll.service;

import org.springframework.stereotype.Service;

import com.microservice.hrpayroll.entities.Payment;

@Service
public class PaymentService {

	public Payment getPayment(Long workerId, Integer days) {
		return new Payment("Thiago", 30.0, days);
	}
}

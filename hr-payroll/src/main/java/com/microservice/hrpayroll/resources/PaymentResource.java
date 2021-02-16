package com.microservice.hrpayroll.resources;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.microservice.hrpayroll.entities.Payment;
import com.microservice.hrpayroll.service.PaymentService;

@RestController
@RequestMapping(value = "/payment")
public class PaymentResource {

	@Autowired
	private PaymentService service;

	@GetMapping(value = "/{workerId}/days/{days}")
	public ResponseEntity<Payment> getPayment(@PathVariable Long workerId, @PathVariable Integer days) {
		return ResponseEntity.ok(service.getPayment(workerId, days));
	}
}

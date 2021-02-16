package com.microservice.hrpayroll.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.microservice.hrpayroll.WorkerFeignClient;
import com.microservice.hrpayroll.entities.Payment;
import com.microservice.hrpayroll.entities.Worker;

@Service
public class PaymentService {

	@Autowired
	private WorkerFeignClient workerFeignClient;

	public Payment getPayment(long workerId, Integer days) {
		Worker worker = workerFeignClient.getById(workerId).getBody();
		return new Payment(worker.getName(), worker.getDailyIncome(), days);
	}
}

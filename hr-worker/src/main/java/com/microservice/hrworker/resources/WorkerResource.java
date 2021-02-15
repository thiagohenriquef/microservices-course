package com.microservice.hrworker.resources;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.microservice.hrworker.WorkerRepository;
import com.microservice.hrworker.entities.Worker;

@RestController
@RequestMapping(value = "/workers")
public class WorkerResource {

	@Autowired
	private WorkerRepository repository;

	@GetMapping
	public ResponseEntity<List<Worker>> getAlltWorkers() {
		return ResponseEntity.ok(repository.findAll());
	}

	@GetMapping(value = "/{id}")
	public ResponseEntity<Worker> getById(@PathVariable Long id) {
		Worker worker = repository.findById(id).get();
		return ResponseEntity.ok(worker);
	}
}

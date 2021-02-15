package com.microservice.hrworker;

import org.springframework.data.jpa.repository.JpaRepository;

import com.microservice.hrworker.entities.Worker;

public interface WorkerRepository extends JpaRepository<Worker, Long>{

}

package com.howtodoinjava.example.async.service;

import java.util.concurrent.CompletableFuture;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.howtodoinjava.example.async.model.EmployeeNames;
import com.howtodoinjava.example.async.model.EmployeePhone;

@Service
public class AsyncService {

	private static Logger log = LoggerFactory.getLogger(AsyncService.class);

	@Autowired
	private RestTemplate restTemplate;

	@Bean
	public RestTemplate restTemplate() {
		return new RestTemplate();
	}

	@Async("asyncExecutor")
	public CompletableFuture<EmployeeNames> getEmployeeName(Long nroRequest) throws InterruptedException {
		log.info("getEmployeeName starts para request: " + nroRequest);

		EmployeeNames employeeNameData = restTemplate.getForObject("http://localhost:8080/names", EmployeeNames.class);
		log.info("Datos de employeeNameData, {}", employeeNameData);

		Thread.sleep(20000); // Intentional delay

		log.info("employeeNameData completado para request: " + nroRequest);
		return CompletableFuture.completedFuture(employeeNameData);
	}

	@Async("asyncExecutor")
	public CompletableFuture<EmployeePhone> getEmployeePhone(Long nroRequest) throws InterruptedException {
		log.info("getEmployeePhone starts para request: " + nroRequest);

		EmployeePhone employeePhoneData = restTemplate.getForObject("http://localhost:8080/phones",
				EmployeePhone.class);
		log.info("Datos de employeePhoneData, {}", employeePhoneData);
		
		Thread.sleep(20000L); // Intentional delay
		
		log.info("employeePhoneData completed para request: " + nroRequest);
		return CompletableFuture.completedFuture(employeePhoneData);
	}
}
package com.howtodoinjava.example.sampleservice.controller;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicLong;
import java.util.function.Supplier;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.howtodoinjava.example.async.model.EmployeeNames;
import com.howtodoinjava.example.async.service.AsyncService;

@RestController
public class AsyncController {

	private static Logger log = LoggerFactory.getLogger(AsyncController.class);

	@Autowired
	private AsyncService service;

	private final AtomicLong counter = new AtomicLong();

	@RequestMapping(value = "/testAsynch", method = RequestMethod.GET)
	public ResponseEntity<Object> testAsynch() throws InterruptedException, ExecutionException {
		Long contador = counter.incrementAndGet();
		log.info("GET /testAsynch " + contador.toString() + " Inicio");

		//CompletableFuture<EmployeeNames> employeeName = service.getEmployeeName(contador);
		//CompletableFuture<EmployeePhone> employeePhone = service.getEmployeePhone(contador);

		// Esperamos hasta que se completen todos
		// CompletableFuture.allOf(employeeName, employeePhone).join();
		// log.info("EmployeeName--> " + employeeName.get());
		
		
		// Otra forma de hacerlo seria
		CompletableFuture<ResponseEntity> proceso = CompletableFuture.supplyAsync(new Supplier<ResponseEntity>() {
		    @Override
		    public ResponseEntity get() {
		        try {
		            TimeUnit.SECONDS.sleep(10);
		        } catch (InterruptedException e) {
		            throw new IllegalStateException(e);
		        }
		        return ResponseEntity.ok("Muy bien, ya termino el proceso.");
		    }
		});

		log.info(contador.toString() + " testAsynch Fin");
		return ResponseEntity.ok("Se recibió el pedido de datos del Empleado");
	}
	
	public ResponseEntity<Object> getEmployed() throws InterruptedException {
		CompletableFuture<EmployeeNames> employeeName = service.getEmployeeName(33L);
		
		return ResponseEntity.ok("Se esta ejecutando getEmployed");
	}
}
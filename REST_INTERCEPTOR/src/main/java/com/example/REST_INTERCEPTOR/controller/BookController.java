package com.example.REST_INTERCEPTOR.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.REST_INTERCEPTOR.model.Book;

@RestController
public class BookController {

	private static Map<Integer, Book> bookstore = new HashMap<Integer, Book>();

	static {
		Book b1 = new Book(1, "Asi hablaba Zaratustra", "Nietsche");
		Book b2 = new Book(2, "El Proceso", "Kafka");
		Book b3 = new Book(3, "La Resistencia", "Sabato");

		bookstore.put(b1.getId(), b1);
		bookstore.put(b2.getId(), b2);
		bookstore.put(b3.getId(), b3);
	}

	@RequestMapping(value = "/book")
	public ResponseEntity<Object> getBook(@RequestParam("id") Integer bookId) {
		System.out.println("Se procesa el Request");

		return bookstore.containsKey(bookId) ? new ResponseEntity<>(bookstore.get(bookId), HttpStatus.OK)
				: new ResponseEntity<>("No se encontro el libro", HttpStatus.OK);
	}
}
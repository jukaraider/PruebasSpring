package com.example.REST_DB;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.example.REST_DB.model.Product;
import com.example.REST_DB.repository.ProductRepository;

@SpringBootTest
class RestDbApplicationTests {

	private static final Logger log = LoggerFactory.getLogger(RestDbApplicationTests.class);

	@Autowired
	private ProductRepository productRepository;

	@Test
	public void findAllUsers() {
		List<Product> products = productRepository.findAll();
		assertNotNull(products);
		assertTrue(!products.isEmpty());
		assertTrue(products.size() > 1);
		assertTrue(products.stream().anyMatch(u -> u.getName().equals("Monitor")));
	}

	@Test
	public void findProductById() {
		Optional<Product> p = productRepository.findById(1);
		assertNotNull(p);
		Product product = p.get();
		log.info("Product: " + product.getName() + " - " + product.getCategory());
		assertEquals(product.getName(), "Monitor");
	}

	@Test
	public void createProduct() {
		Product product = new Product("Printer", "Auxiliary Electronics");
		Product savedProduct = productRepository.save(product);
		Product newProduct = productRepository.findById(savedProduct.getId()).get();
		assertEquals("Printer", newProduct.getName());
		assertEquals("Auxiliary Electronics", newProduct.getCategory());
	}
}

package com.example.pp3.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.pp3.model.Product;
import com.example.pp3.service.ProductService;

@RestController
public class ProductController {

	@Autowired
	private ProductService productService;

	@GetMapping("/products")
	public List<Product> getProducts() {
		return productService.getAllProducts();
	}

	@GetMapping("/products/{id}")
	public Product getProduct(@PathVariable("id") String idProduct) {
		return productService.getProduct(idProduct);
	}

	@PostMapping("/products")
	public void addProduct(@RequestBody Product product) {
		productService.addProduct(product);
	}

	@PutMapping("/products/{id}")
	public void putProduct(@PathVariable("id") String idProduct, @RequestBody Product product) {
		productService.updateProduct(idProduct, product);
	}

	@DeleteMapping("/products/{id}")
	public void deleteProduct(@PathVariable("id") String idProduct) {
		productService.deleteProduct(idProduct);
	}
}
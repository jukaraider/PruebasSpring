package com.example.REST_DB.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.REST_DB.model.Product;
import com.example.REST_DB.service.ProductService;
import com.example.REST_DB.service.ProductServiceWithCache;

@RestController
public class ProductController {

	@Autowired
	private ProductService productService;
	@Autowired
	private ProductServiceWithCache productServiceCache;

	@GetMapping("/products")
	public List<Product> getProducts() {
		return productService.getAllProducts();
	}
	
	@GetMapping("/productsCache")
	public List<Product> getProductsCache() {
		return productServiceCache.getAllProductsWithCache();
	}

	@GetMapping("/products/{id}")
	public Product getProduct(@PathVariable("id") Integer idProduct) {
		return productService.getProduct(idProduct);
	}
	
	@GetMapping("/productsCache/{id}")
	public Product getProductCache(@PathVariable("id") Integer idProduct) {
		return productServiceCache.getProductWithCache(idProduct);
	}

	@PostMapping("/products")
	public void addProduct(@RequestBody Product product) {
		productService.addProduct(product);
	}
	
	@PostMapping("/productsCache")
	public void addProductCache(@RequestBody Product product) {
		productServiceCache.addProductWithCache(product);
	}

	@PutMapping("/products/{id}")
	public void putProduct(@PathVariable("id") Integer idProduct, @RequestBody Product product) {
		productService.updateProduct(idProduct, product);
	}
	
	@PutMapping("/productsCache/{id}")
	public void putProductCache(@PathVariable("id") Integer idProduct, @RequestBody Product product) {
		productServiceCache.updateProductWithCache(product, idProduct);
	}

	@DeleteMapping("/products/{id}")
	public void deleteProduct(@PathVariable("id") Integer idProduct) {
		productService.deleteProduct(idProduct);
	}
	
	@DeleteMapping("/productsCache/{id}")
	public void deleteProductCache(@PathVariable("id") Integer idProduct) {
		productServiceCache.deleteProductWithCache(idProduct);
	}
}
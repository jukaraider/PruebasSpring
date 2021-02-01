package com.example.REST_DB.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.REST_DB.exception.NotFoundException;
import com.example.REST_DB.model.Product;
import com.example.REST_DB.repository.ProductRepository;

@Service
public class ProductService {

	@Autowired
	private ProductRepository productRepository;

	public List<Product> getAllProducts() {
//		List<Product> products = new ArrayList<Product>();
//		productRepository.findAll().forEach(products::add);
//		return products;

		return productRepository.findAll();
	}

	public Product getProduct(Integer id) {
		return productRepository.findById(id).get();
	}

	public void addProduct(Product p) {
		productRepository.save(p);
	}

	public void updateProduct(Integer id, Product p) {
		if (productRepository.existsById(id)) {
			productRepository.save(p);
		} else {
			throw new NotFoundException(id.toString());
		}
	}

	public void deleteProduct(Integer id) {
		productRepository.deleteById(id);
	}
}
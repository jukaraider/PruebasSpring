package com.example.pp3.service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.stereotype.Service;

import com.example.pp3.exception.NotFoundException;
import com.example.pp3.model.Product;

@Service
public class ProductService {

	private List<Product> products = new ArrayList<Product>(
			Arrays.asList(new Product("a1", "Monitor", "Electronics"), new Product("a2", "Teclado", "Electronics"),
					new Product("a3", "Silla", "Fourniture"), new Product("a4", "Mesa", "Fourniture")));

	public List<Product> getAllProducts() {
		return products;
	}

	public Product getProduct(String id) {
		return products.stream().filter(p -> id.equals(p.getId())).findFirst().get();
	}

	public void addProduct(Product p) {
		products.add(p);
	}

	public void updateProduct(String id, Product p) {
		for (int i = 0; i < products.size(); i++) {
			Product product = products.get(i);
			if (product.getId().equals(id)) {
				products.set(i, p);
				return;
			}
		}
		throw new NotFoundException(id);
	}

	public void deleteProduct(String id) {
		products.removeIf(p -> p.getId().equals(id));
	}
}
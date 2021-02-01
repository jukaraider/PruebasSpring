package com.example.REST_DB.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.example.REST_DB.model.Product;

@Repository
public interface ProductRepository extends JpaRepository<Product, Integer> {

	Product findByCategory(String category);

	List<Product> findByNameLike(String name);

	@Query("Select u from Product u where u.name like %?1%")
	List<Product> searchByName(String name);

}
package com.example.REST_DB.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.Caching;
import org.springframework.stereotype.Service;

import com.example.REST_DB.exception.NotFoundException;
import com.example.REST_DB.model.Product;
import com.example.REST_DB.repository.ProductRepository;

@Service
public class ProductServiceWithCache {

	@Autowired
	private ProductRepository productRepository;

	/*
	 * Para probar la logica cacheable: la primera vez le mandamos un sleep y luego
	 * vamos a ver que la cache funciona porque no entra mas a este metodo y
	 * devuelve los datos de la cache. Esta cache se llama "products" es importante
	 * porque despues vamos a tener que ir actualizandola.
	 */
	@Cacheable("products")
	public List<Product> getAllProductsWithCache() {
		try {
			Thread.sleep(3000);
		} catch (InterruptedException e) {
			throw new IllegalStateException(e);
		}
		return productRepository.findAll();
	}

	@Cacheable(value = "product", key = "#p0")
	public Product getProductWithCache(Integer id) {
		try {
			Thread.sleep(3000);
		} catch (InterruptedException e) {
			throw new IllegalStateException(e);
		}
		return productRepository.findById(id).get();
	}

	/*
	 * Un problema que aparece cuando manejamos logica cacheable es que algunos
	 * objetos se toman de la cache y luego los listados no se actualizan, por eso
	 * para ciertos metodos hay que decirle que tambien actualice la cache. En este
	 * caso al crear/actualizar/borrar un objeto, tambien actualiza las caches en
	 * donde este el objeto. Actualizamos el listado "products"
	 */
	@CacheEvict(value = "products", allEntries = true)
	public void addProductWithCache(Product product) {
		productRepository.save(product);
	}

	/*
	 * Actualizamos el listado "products" y la cache que devuelve el "product" #p0
	 * -> Primer parametro del metodo #p1 -> Segundo parametro del metodo ...
	 */
	@Caching(evict = { 
			@CacheEvict(value = "products", allEntries = true),
			@CacheEvict(value = "product", key = "#p1") })
	public void updateProductWithCache(Product product, Integer id) {
		if (productRepository.existsById(id)) {
			productRepository.save(product);
		} else {
			throw new NotFoundException(id.toString());
		}
	}

	/*
	 * Actualizamos el listado "products" y la cache que devuelve el "product" #id
	 * -> Parametro llamado id, si hubiese sido un objeto Producto podriamos poner
	 * #producto.id o #producto.name ...
	 */
	@Caching(evict = { 
			@CacheEvict(value = "products", allEntries = true),
			@CacheEvict(value = "product", key = "#id") })
	public void deleteProductWithCache(Integer id) {
		productRepository.deleteById(id);
	}
}
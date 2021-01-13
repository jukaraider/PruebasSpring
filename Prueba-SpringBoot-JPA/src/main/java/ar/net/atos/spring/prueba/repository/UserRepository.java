package ar.net.atos.spring.prueba.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import ar.net.atos.spring.prueba.entity.User;

public interface UserRepository extends JpaRepository<User, Integer> {

	User findByEmail(String email);
	
	List<User> findByNameLike(String name);
	
	@Query("select u from User u where u.name like %?1%")
	List<User> searchByName(String name);
	
}
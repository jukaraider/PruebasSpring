package ar.net.atos.spring.prueba;

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

import ar.net.atos.spring.prueba.entity.User;
import ar.net.atos.spring.prueba.repository.UserRepository;

@SpringBootTest
class PruebaSpringBootJpaApplicationTests {
	private static final Logger log = LoggerFactory.getLogger(PruebaSpringBootJpaApplicationTests.class);

	@Autowired
	private UserRepository userRepository;

	@Test
	public void findAllUsers() {
		List<User> users = userRepository.findAll();
		assertNotNull(users);
		assertTrue(!users.isEmpty());
		assertTrue(users.size() > 1);
		assertTrue(users.stream().anyMatch(u -> u.getName().equals("John")));
	}

	@Test
	public void findUserById() {
		Optional<User> user = userRepository.findById(-1);
		assertNotNull(user.get());
		log.info("User: " + ((User) user.get()).getName() + " - " + ((User) user.get()).getEmail());
		assertEquals(((User) user.get()).getName(), "John");
	}

	@Test
	void distintasFormasDeHacerLoMismo() {
		//findByEmail, findByNameLike, searchByName son 3 metodos que hacen lo mismo pero se implementan distinto. Ver UserRepository
		User unUser = userRepository.findByEmail("rod@gmail.com");
		assertEquals("Rod", unUser.getName());
		List<User> usuarios = userRepository.findByNameLike("Rod");
		List<User> losMismosUsuarios = userRepository.searchByName("Rod");
		assertTrue(usuarios.stream().allMatch(u -> losMismosUsuarios.stream().anyMatch(t -> u.getName().equals(t.getName()))));
	}

	@Test
	public void createUser() {
		User user = new User("Paul", "paul@gmail.com");
		User savedUser = userRepository.save(user);
		User newUser = userRepository.findById(savedUser.getId()).get();
		assertEquals("Paul", newUser.getName());
		assertEquals("paul@gmail.com", newUser.getEmail());
	}
}

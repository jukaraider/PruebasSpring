package ar.net.atos.spring.basic.repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import ar.net.atos.spring.basic.dao.UserRowMapper;
import ar.net.atos.spring.basic.entity.User;

@Repository
public class UserRepository {
	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	private static final Logger log = LoggerFactory.getLogger(UserRepository.class);

	@Transactional(readOnly = true)
	public List<User> findAll() {
		log.trace("Buscando a todos los usuarios");
		log.debug("Buscando a todos los usuarios");
		log.info("Buscando a todos los usuarios");
		log.warn("Buscando a todos los usuarios");
		log.error("Buscando a todos los usuarios");
		
		return jdbcTemplate.query("select * from users", new UserRowMapper());
	}

	@Transactional(readOnly = true)
	public User findUserById(int id) {
		return jdbcTemplate.queryForObject("select * from users where id=?", new Object[] { id }, new UserRowMapper());
	}

	public User create(final User user) {
		final String sql = "insert into users(name, email) values(?, ?)";

		KeyHolder holder = new GeneratedKeyHolder();

		jdbcTemplate.update(new PreparedStatementCreator() {
			@Override
			public PreparedStatement createPreparedStatement(Connection connection) throws SQLException {
				PreparedStatement ps = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
				ps.setString(1, user.getName());
				ps.setString(2, user.getEmail());
				return ps;
			}
		}, holder);

		int newUserId = holder.getKey().intValue();
		user.setId(newUserId);
		return user;
	}
}

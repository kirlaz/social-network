package com.lazarenko.socialnetwork.repositories;

import com.lazarenko.socialnetwork.entities.Gender;
import com.lazarenko.socialnetwork.entities.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import java.sql.ResultSet;
import java.util.List;
import java.util.UUID;

@Component
public class UserRepository {

    private static final Logger LOGGER = LoggerFactory.getLogger(UserRepository.class);

    private final JdbcTemplate jdbcTemplate;

    RowMapper<User> ROW_MAPPER = (ResultSet resultSet, int rowNum) ->
            new User(resultSet.getString("id"),
                    resultSet.getString("login"),
                    resultSet.getString("password"),
                    resultSet.getString("firstName"),
                    resultSet.getString("lastName"),
                    resultSet.getInt("age"),
                    resultSet.getString("gender") == null ? null : Gender.valueOf(resultSet.getString("gender")),
                    resultSet.getString("interests"),
                    resultSet.getString("city"),
                    resultSet.getString("email")
            );

    @Autowired
    public UserRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public User save(User user) {
        if (user.getId() == null) {
            user.setId(UUID.randomUUID().toString());
            jdbcTemplate.update("insert into user values (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)",
                    user.getId(), user.getLogin(), user.getPassword(), user.getFirstName(), user.getLastName(), user.getAge(), user.getGender(), user.getInterests(), user.getCity(), user.getEmail());
        } else {
            jdbcTemplate.update("update user set " +
                            "login = ?1, password = ?2, firstName = ?3, lastName = ?4, age = ?5, gender = ?6, interests = ?7, city = ?8, email = ?9",
                    user.getLogin(), user.getPassword(), user.getFirstName(), user.getLastName(), user.getAge(), user.getGender(), user.getInterests(), user.getCity(), user.getEmail());
        }

        return findById(user.getId());
    }

    public List<User> findAll() {
        return jdbcTemplate.query("select * from User", ROW_MAPPER);
    }

    public User findById(String id) {
        User person = null;
        try {
            person = jdbcTemplate.queryForObject("select * from User where id = ?", new Object[]{id}, ROW_MAPPER);
        } catch (DataAccessException dataAccessException) {
            LOGGER.warn("Couldn't find entity of type User with id {}", id);
        }
        return person;
    }

    User findByLogin(String login) {
        User person = null;
        try {
            person = jdbcTemplate.queryForObject("select * from User where login = ?", new Object[]{login}, ROW_MAPPER);
        } catch (DataAccessException dataAccessException) {
            LOGGER.warn("Couldn't find entity of type User with id {}", login);
        }
        return person;
    }

    public int delete(String id) {
        return jdbcTemplate.update("delete from User where id = ?", id);
    }

    public User findByUniqueFields(String email, String login) {
        User user = null;
        try {
            user = jdbcTemplate.queryForObject("select * from User where email = ? or login = ?",
                    new Object[]{email, login}, ROW_MAPPER);
        } catch (DataAccessException dataAccessException) {
            LOGGER.debug("Couldn't find entity of type User with email {} or login {}", email, login);
        }
        return user;
    }
}

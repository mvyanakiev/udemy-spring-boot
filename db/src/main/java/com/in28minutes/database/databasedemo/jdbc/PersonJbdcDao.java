package com.in28minutes.database.databasedemo.jdbc;

import com.in28minutes.database.databasedemo.entity.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public class PersonJbdcDao {
    @Autowired
    JdbcTemplate jdbcTemplate;

    public List<Person> findAll() {
        return jdbcTemplate.query("SELECT * FROM person",
            new BeanPropertyRowMapper(Person.class));
    }

    public Person findById(int id) {
        return jdbcTemplate.queryForObject
            ("SELECT * FROM person WHERE id = ?", new Object[] { id },
                new BeanPropertyRowMapper<Person>(Person.class));
    }

    public List<Person> findByLocation(String location) {
        return jdbcTemplate.query("SELECT * FROM person WHERE location = ?", new Object[] { location },
            new BeanPropertyRowMapper(Person.class));
    }
}

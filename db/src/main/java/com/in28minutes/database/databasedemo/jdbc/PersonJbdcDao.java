package com.in28minutes.database.databasedemo.jdbc;

import com.in28minutes.database.databasedemo.entity.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
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

    public List<Person> findByName(String name) {
        return jdbcTemplate.query ("SELECT id, name FROM person WHERE name = ?", new Object[] { name },
            new PersonRowMapper());
    }

    public int deleteById(int id) {
        return jdbcTemplate.update
            ("DELETE FROM person WHERE id = ?", new Object[] { id });
    }

    public int insert(Person person) {
        return jdbcTemplate.update
            ("insert into person (id, name, location, birth_date ) values (?, ?, ?, ?)",
                new Object[] { person.getId(), person.getName(), person.getLocation(), new Timestamp(person.getBirthDate().getTime()) });
    }

    public int update(Person person) {
        return jdbcTemplate.update
            ("update person set name = ?, location = ?, birth_date = ? where id = ?",
                new Object[] { person.getName(), person.getLocation(), new Timestamp(person.getBirthDate().getTime()), person.getId() });
    }

    class PersonRowMapper implements RowMapper<Person> {
        @Override
        public Person mapRow(ResultSet rs, int rowNum) throws SQLException {
            Person person = new Person();
            person.setId(rs.getInt("id"));
            person.setName(rs.getString("name"));
//            person.setLocation(rs.getString("location"));
//            person.setBirthDate(rs.getTimestamp("birth_date"));
            return person;
        }
    }
}

package springcourse.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import springcourse.models.Person;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Component
public class PersonDAO {

    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public PersonDAO(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public List<Person> index() {
        return jdbcTemplate.query("select * from person",
                new PersonMapper());
    }


    public Person show(int id) {
        return jdbcTemplate.query("select * from person where id = ?",
                new Object[]{id},
                new BeanPropertyRowMapper<>(Person.class))
                .stream().findFirst().orElse(null);
    }


    public void save(Person person) {
        jdbcTemplate.update("INSERT INTO person VALUES (1, ?, ?, ?)",
                person.getName(), person.getAge(), person.getEmail());
    }


    public void update(int id, Person updatePerson) {
        jdbcTemplate.update("UPDATE Person SET name=?, age=?, email=? WHERE id=?",
                updatePerson.getName(),
                updatePerson.getAge(),
                updatePerson.getEmail(),
                id);
    }

    public void delete(int id) {
        jdbcTemplate.update("DELETE FROM person WHERE id=?", id);
    }
}

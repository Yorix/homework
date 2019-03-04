package com.yorix.springpersistence.students;

import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.List;

public class StudentsJdbcService implements StudentsService {
    private JdbcTemplate jdbcTemplate;

    StudentsJdbcService(JdbcTemplate jdbcTemplate, List<Student> predefinedStudents) {
        this.jdbcTemplate = jdbcTemplate;
        predefinedStudents.forEach(this::create);
    }

    @Override
    public List<Student> readAll() {
        return jdbcTemplate.query("SELECT `id`, `name` FROM `students`", new BeanPropertyRowMapper<>(Student.class));
    }

    @Override
    public Student read(int id) {
        return jdbcTemplate.queryForObject("SELECT `id`, `name` FROM `students` WHERE `id` = ?", new BeanPropertyRowMapper<>(Student.class), id);
    }

    @Override
    public Student create(Student student) {
        jdbcTemplate.update(
                "INSERT INTO `students`(`name`) VALUES (?)",
                student.getName()
        );
        Integer id = jdbcTemplate.queryForObject(
                "SELECT `id` FROM `students` ORDER BY `id` DESC LIMIT 1", Integer.TYPE
        );
        student.setId(id);
        return student;
    }

    @Override
    public Student update(Student student) {
        int rows = jdbcTemplate.update(
                "UPDATE `students` SET `name` = ? WHERE `id` = ?",
                student.getName(),
                student.getId()
        );
        if (rows == 1)
            return student;
        else
            throw new EmptyResultDataAccessException(1);
    }

    @Override
    public int delete(int id) {
        int rows = jdbcTemplate.update("DELETE FROM `students` WHERE `id` = ?", id);
        if (rows == 1)
            return 1;
        else
            throw new EmptyResultDataAccessException(1);
    }
}

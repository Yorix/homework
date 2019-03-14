package com.yorix.springpersistence.storage;

import com.yorix.springpersistence.students.Student;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.List;

public class StudentsJdbcDao implements StudentsDao {
    private JdbcTemplate jdbcTemplate;

    public StudentsJdbcDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public Student create(Student student) {
        jdbcTemplate.update(
                "INSERT INTO `student`(`name`) VALUES (?)",
                student.getName()
        );
        Integer id = jdbcTemplate.queryForObject(
                "SELECT `id` FROM `student` ORDER BY `id` DESC LIMIT 1", Integer.TYPE
        );
        student.setId(id);
        return student;
    }

    @Override
    public Student read(int id) {
        return jdbcTemplate.queryForObject("SELECT `id`, `name` FROM `student` WHERE `id` = ?", new BeanPropertyRowMapper<>(Student.class), id);
    }

    @Override
    public List<Student> readAll() {
        return jdbcTemplate.query("SELECT `id`, `name` FROM `student`", new BeanPropertyRowMapper<>(Student.class));
    }

    @Override
    public int update(Student student) {
        return jdbcTemplate.update(
                "UPDATE `student` SET `name` = ? WHERE `id` = ?",
                student.getName(),
                student.getId()
        );
    }

    @Override
    public int delete(int id) {
        return jdbcTemplate.update("DELETE FROM `student` WHERE `id` = ?", id);
    }
}

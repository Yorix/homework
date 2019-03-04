package com.yorix.springpersistence.students;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.List;

@Configuration
public class StudentsConfiguration {

//    @Bean
//    public CommandLineRunner databaseInitialRunner(JdbcTemplate jdbcTemplate) {
//        return args -> jdbcTemplate.execute("DROP SCHEMA IF EXISTS school CASCADE; " +
//                "CREATE SCHEMA school; " +
//                "USE school; " +
//                "CREATE TABLE students( " +
//                "  id INT AUTO_INCREMENT PRIMARY KEY, " +
//                "  name VARCHAR(50));");
//    }

    @Bean
    public StudentsDao studentsInMemoryDao() {
        return new StudentsInMemoryDao();
    }

    @Bean
    @Primary
    public StudentsDao studentsJdbcDao(JdbcTemplate jdbcTemplate) {
        return new StudentsJdbcDao(jdbcTemplate);
    }

    @Bean
    public List<Student> predefinedStudents() {
        return List.of(
                new Student(1, "John Doe"),
                new Student(2, "Jane Roe"),
                new Student(3, "James Raynor"),
                new Student(4, "Sarah Kerrigan")
        );
    }
}

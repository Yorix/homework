package com.yorix.springpersistence.students;

import com.yorix.springpersistence.storage.StudentsDao;
import com.yorix.springpersistence.storage.StudentsHibernateDao;
import com.yorix.springpersistence.storage.StudentsInMemoryDao;
import com.yorix.springpersistence.storage.StudentsJdbcDao;
import org.hibernate.SessionFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.orm.hibernate5.HibernateTransactionManager;

import javax.persistence.EntityManagerFactory;
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
//    @Primary
    public StudentsDao studentsJdbcDao(JdbcTemplate jdbcTemplate) {
        return new StudentsJdbcDao(jdbcTemplate);
    }

    @Bean
    public StudentsDao studentsHibernateDao() {
        return new StudentsHibernateDao();
    }

//    @Bean
//    public HibernateTransactionManager hibernateTransactionManager(EntityManagerFactory entityManagerFactory) {
//        return new HibernateTransactionManager(entityManagerFactory.unwrap(SessionFactory.class));
//    }

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

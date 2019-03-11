package com.yorix.springpersistence.storage;

import com.yorix.springpersistence.students.Student;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StudentsRepository extends JpaRepository<Student, Integer> {
}

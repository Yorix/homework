package com.yorix.springpersistence.storage;

import com.yorix.springpersistence.students.Student;

import java.util.List;

public interface StudentsDao {

    Student create(Student student);

    Student read(int id);

    List<Student> readAll();

    int update(Student student);

    int delete(int id);
}

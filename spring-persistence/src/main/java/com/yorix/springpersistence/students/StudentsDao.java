package com.yorix.springpersistence.students;

import java.util.List;

public interface StudentsDao {

    Student create(Student student);

    Student read(int id);

    List<Student> readAll();

    int update(Student student);

    int delete(int id);
}

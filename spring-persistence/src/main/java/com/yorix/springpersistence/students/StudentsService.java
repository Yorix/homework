package com.yorix.springpersistence.students;

import java.util.List;

public interface StudentsService {
    List<Student> readAll();

    Student read(int id);

    Student create(Student student);

    Student update(Student student);

    int delete(int id);
}

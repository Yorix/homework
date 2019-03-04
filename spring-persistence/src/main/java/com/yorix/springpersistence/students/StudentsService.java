package com.yorix.springpersistence.students;

import java.util.List;

public interface StudentsService {
    List<Student> readAll();

    Student read(int id);

    void create(Student student);

    void update(Student student);

    void delete(int id);
}

package com.yorix.springpersistence.storage;

import com.yorix.springpersistence.students.Student;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class StudentsInMemoryDao implements StudentsDao {
    private Map<Integer, Student> studentsMap = new HashMap<>();
    private int id;

    @Override
    public List<Student> readAll() {
        return new ArrayList<>(studentsMap.values());
    }

    @Override
    public Student read(int id) {
        return studentsMap.get(id);
    }

    @Override
    public Student create(Student student) {
        student.setId(id++);
        studentsMap.put(student.getId(), student);
        return student;
    }

    @Override
    public int update(Student student) {
        student = studentsMap.put(student.getId(), student);
        return student != null ? 1 : 0;
    }

    @Override
    public int delete(int id) {
        if (studentsMap.remove(id) == null) {
            return 0;
        } else return 1;
    }
}

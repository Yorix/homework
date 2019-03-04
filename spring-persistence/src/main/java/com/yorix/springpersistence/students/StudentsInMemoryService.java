package com.yorix.springpersistence.students;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class StudentsInMemoryService implements StudentsService {
    private Map<Integer, Student> studentsMap = new HashMap<>();
    private int id;

    StudentsInMemoryService(List<Student> predefinedStudents) {
        predefinedStudents.forEach(this::create);
    }

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
    public Student update(Student student) {
        studentsMap.put(student.getId(), student);
        return student;
    }

    @Override
    public int delete(int id) {
        if (studentsMap.remove(id) == null) {
            return 0;
        } else return 1;
    }
}

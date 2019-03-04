package com.yorix.springpersistence.students;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

@Service
public class StudentsServiceImpl implements StudentsService {
    private final StudentsDao studentsDao;

    @Autowired
    StudentsServiceImpl(StudentsDao studentsDao, List<Student> predefinedStudents) {
        this.studentsDao = studentsDao;
        Map<String, String> map = new HashMap<>();
        Iterator<Student> iterator = predefinedStudents.iterator();
        while (iterator.hasNext())
            create(iterator.next());
//        predefinedStudents.forEach(this::create);
    }

    @Override
    public List<Student> readAll() {
        return studentsDao.readAll();
    }

    @Override
    public Student read(int id) {
        return studentsDao.read(id);
    }

    @Override
    public void create(Student student) {
        studentsDao.create(student);
    }

    @Override
    public void update(Student student) {
        int rows = studentsDao.update(student);
        if (rows != 1)
            throw new EmptyResultDataAccessException(1);
    }

    @Override
    public void delete(int id) {
        int rows = studentsDao.delete(id);
        if (rows != 1)
            throw new EmptyResultDataAccessException(1);
    }
}

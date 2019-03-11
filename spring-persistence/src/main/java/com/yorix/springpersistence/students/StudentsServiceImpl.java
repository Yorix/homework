package com.yorix.springpersistence.students;

import com.yorix.springpersistence.storage.StudentsDao;
import com.yorix.springpersistence.storage.StudentsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StudentsServiceImpl implements StudentsService {
    private final StudentsDao studentsDao;
    private final StudentsRepository studentsRepository;

    @Autowired
    StudentsServiceImpl(
//            @Qualifier("studentsJdbcDao") StudentsDao studentsDao,
//            @Qualifier("studentsInMemoryDao") StudentsDao studentsDao,
//            @Qualifier("studentsHibernateDao") StudentsDao studentsDao,
            StudentsRepository studentsRepository,
            List<Student> predefinedStudents) {
        this.studentsDao = null; // = studentsDao;
        this.studentsRepository = studentsRepository;
        predefinedStudents.forEach(this::create);
    }

    @Override
    public List<Student> readAll() {
        if (isDaoConfigured())
            return studentsDao.readAll();
        return studentsRepository.findAll();
    }

    @Override
    public Student read(int id) {
        if (isDaoConfigured())
            return studentsDao.read(id);
        return studentsRepository.findById(id)
                .orElseGet(() -> {
                    throw new EmptyResultDataAccessException(1);
                });
    }

    @Override
    public void create(Student student) {
        if (isDaoConfigured())
            studentsDao.create(student);
        else studentsRepository.saveAndFlush(student);
    }

    @Override
    public void update(Student student) {
        if (isDaoConfigured()) {
            int rows = studentsDao.update(student);
            if (rows != 1)
                throw new EmptyResultDataAccessException(1);
        } else {
            studentsRepository.findById(student.getId())
                    .orElseGet(() -> {
                        throw new EmptyResultDataAccessException(1);
                    });

            studentsRepository.saveAndFlush(student);
        }
    }

    @Override
    public void delete(int id) {
        if (isDaoConfigured()) {
            int rows = studentsDao.delete(id);
            if (rows != 1)
                throw new EmptyResultDataAccessException(1);
        } else studentsRepository.deleteById(id);
    }


    private boolean isDaoConfigured() {
        return studentsDao != null;
    }
}

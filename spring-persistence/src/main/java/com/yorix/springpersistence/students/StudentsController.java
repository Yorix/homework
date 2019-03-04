package com.yorix.springpersistence.students;

import lombok.Data;
import lombok.NonNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/students")
public class StudentsController {
    private StudentsService studentsService;

    @Autowired
    public StudentsController(@Qualifier("studentsJdbcService") StudentsService studentsService) {
        this.studentsService = studentsService;
    }

    @GetMapping
    public List<Student> all() {
        return studentsService.readAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Student> read(@PathVariable("id") int id) {
        Student student = studentsService.read(id);
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(student);
    }

    @PostMapping
    public ResponseEntity<Student> create(@RequestBody String name) {
        Student student = new Student(name);
        studentsService.create(student);
        return ResponseEntity.status(HttpStatus.CREATED)
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .body(student);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Student> update(@RequestBody String name, @PathVariable int id) {
        Student student = new Student(id, name);
        studentsService.update(student);
        return ResponseEntity
                .status(HttpStatus.OK)
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .body(student);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity delete(@PathVariable("id") int id) {
        studentsService.delete(id);
        return ResponseEntity
                .status(HttpStatus.OK)
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .build();
    }

    @ExceptionHandler
    public ResponseEntity<ErrorMessage> errorHandle(Exception e) {
        ErrorMessage errorMessage;
        if (e instanceof EmptyResultDataAccessException) {
            errorMessage = new ErrorMessage("No such resource");
        } else {
            errorMessage = new ErrorMessage(e.getMessage());
        }

        return ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .body(errorMessage);
    }

    @Data
    private class ErrorMessage {
        @NonNull
        private String message;
    }
}

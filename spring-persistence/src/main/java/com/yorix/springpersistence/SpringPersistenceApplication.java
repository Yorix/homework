package com.yorix.springpersistence;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import java.sql.*;

@SpringBootApplication
@Controller
public class SpringPersistenceApplication {
    public static void main(String[] args) {
        SpringApplication.run(SpringPersistenceApplication.class, args);
    }

    @GetMapping("/")
    public String index() {
        return "forward:/students";
    }
}

DROP SCHEMA IF EXISTS school;
CREATE SCHEMA school;
USE school;

drop table if exists student, students;

CREATE TABLE student(
  id INT AUTO_INCREMENT PRIMARY KEY,
  name VARCHAR(50));

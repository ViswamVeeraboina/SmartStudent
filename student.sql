CREATE DATABASE smartstudent;

USE smartstudent;

CREATE TABLE students (
    id INT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(100),
    roll_no VARCHAR(20),
    department VARCHAR(50),
    email VARCHAR(100),
    phone VARCHAR(15),
    marks INT
);

package com.example.mybatis.service;

import java.util.List;

import com.example.mybatis.domin.Student;
public interface StudentService {
    int addStudent(Student student);
    List<Student> quartStudents();
}

package com.example.mybatis.dao;

import java.util.List;
import com.example.mybatis.domin.Student;

public interface StudentDao {
    int insertStudent(Student student);
    List<Student> selectStudents();
}

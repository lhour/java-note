package com.example.mybatis.service.Impl;

import java.util.List;
import com.example.mybatis.dao.StudentDao;
import com.example.mybatis.domin.Student;
import com.example.mybatis.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component("studentService")
public class StudentServiceImpl implements StudentService{
    
    @Autowired
    private StudentDao studentDao;

    public void setStudentDao(StudentDao studentDao){
        this.studentDao = studentDao;
    }

    @Override
    public int addStudent(Student student) {
        int nums = studentDao.insertStudent(student);
        return nums;
    }

    @Override
    public List<Student> quartStudents() {
        List<Student> students = studentDao.selectStudents();
        return students;
    }
    
}

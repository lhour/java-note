package com.example.demo.controller;

import com.example.demo.service.StudentService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class StudentController {
    
    @Autowired
    private StudentService studentService;

    @RequestMapping(value = "/addStudent")
    public @ResponseBody Object addStudent(String key, String value) {
        studentService.put(key, value);

        return "放入 " + key +" : " + value;
    }
}
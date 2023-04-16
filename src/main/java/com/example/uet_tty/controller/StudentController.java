package com.example.uet_tty.controller;

import com.example.uet_tty.entity.Student;
import com.example.uet_tty.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;


@Controller

public class StudentController {
    @Autowired
    StudentService studentService;
    @PostMapping("/student/new")
    public String createStudent(@ModelAttribute Student student){
       return "";
    }
}

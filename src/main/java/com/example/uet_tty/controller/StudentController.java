package com.example.uet_tty.controller;

import com.example.uet_tty.entity.Student;
import com.example.uet_tty.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpSession;


@Controller

public class StudentController {
    @Autowired
    StudentService studentService;
    @PostMapping("/student/new")
    public String createStudent(@ModelAttribute Student student, Model model){
        if(studentService.searchByStudentId(student.getStudent_id())!=null){
            String msg= "Mã sinh viên đã tồn tại";
            model.addAttribute("msg", msg);
            return "new-student.html";
        }
       studentService.create(student);
       return "redirect:/login";
    }

    @GetMapping("/student/profile")
    public String getStudent(Model model, HttpSession session){
        Student student = studentService.searchByUserId((Integer) session.getAttribute("user_id"));
        model.addAttribute("student", student);
        return "student-profile.html";
    }
    @GetMapping("/student/edit")

    public String editStudent(Model model, HttpSession session){

        Student student = studentService.searchByUserId((Integer) session.getAttribute("user_id"));
        model.addAttribute("student", student);
        return "student-edit.html";
    }

    @PostMapping("/student/edit")
    public String editExpert(@ModelAttribute("student") Student student){
        studentService.update(student);
        return "redirect:/student/profile";
    }
}

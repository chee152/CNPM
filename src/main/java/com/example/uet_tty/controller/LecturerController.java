package com.example.uet_tty.controller;

import com.example.uet_tty.entity.Lecturer;
import com.example.uet_tty.service.LecturerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class LecturerController {
    @Autowired
    LecturerService lecturerService;
    @PostMapping("/lecturer/new")
    public String createExpert(@ModelAttribute Lecturer lecturer){
        lecturerService.create(lecturer);
        return "redirect:/login" ;
    }
}

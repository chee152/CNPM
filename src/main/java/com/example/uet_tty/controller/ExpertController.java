package com.example.uet_tty.controller;

import com.example.uet_tty.entity.Expert;
import com.example.uet_tty.service.ExpertService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class ExpertController {
    @Autowired
    ExpertService expertService;
    @PostMapping("/expert/new")
    public String createExpert(@ModelAttribute Expert expert){
        expertService.create(expert);
        return "redirect:/login" ;
    }
}

package com.example.uet_tty.controller;

import com.example.uet_tty.entity.Expert;
import com.example.uet_tty.service.ExpertService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpSession;

@Controller
public class ExpertController {
    @Autowired
    ExpertService expertService;
    @PostMapping("/expert/new")
    public String createExpert(@ModelAttribute Expert expert){
        expertService.create(expert);
        return "redirect:/login" ;
    }

    @GetMapping("/expert/profile")
    public String getExpertProfile(Model model, HttpSession session){
        Expert expert = expertService.getByUserId((Integer) session.getAttribute("user_id"));
        model.addAttribute("expert", expert);
        return "expert-profile.html";
    }
    @GetMapping("/expert/edit")
    public String editExpert(Model model, HttpSession session){
        Expert expert = expertService.getByUserId((Integer) session.getAttribute("user_id"));
        model.addAttribute("expert", expert);
        return "expert-edit.html";
    }

    @PostMapping("/expert/edit")
    public String editExpert(@ModelAttribute("expert") Expert expert){
        expertService.update(expert);
        return "redirect:/expert/profile";
    }
}
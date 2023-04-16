package com.example.uet_tty.controller;

import com.example.uet_tty.entity.User;
import com.example.uet_tty.service.LoginService;
import com.example.uet_tty.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpSession;

@Controller
public class LoginController {
    @Autowired
    LoginService loginService;

    @Autowired
    UserService userService;
    @GetMapping("/login")
    public String login(){
        return "login-page.html";
    }

    @PostMapping("/login")
    public String login(Model model, HttpSession session, @RequestParam("username") String username
            , @RequestParam("password") String password){
        if(loginService.check(username,password)){
            session.setAttribute("username",username);
            return "homepage.html";
        }
        String message="Incorrect username or password";
        model.addAttribute("msg",message);
        return "login-page.html";
    }

    @GetMapping("/signup")
    public String newUser(){
        return "user-signup.html";
    }


    @GetMapping({"/homepage","/"})
    public String homepage(){
        return "homepage.html";
    }
}

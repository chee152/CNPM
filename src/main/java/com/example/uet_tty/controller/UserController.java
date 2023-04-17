package com.example.uet_tty.controller;

import com.example.uet_tty.entity.User;
import com.example.uet_tty.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class UserController {
    @Autowired
    UserService userService;

    @GetMapping("/signup")
    public String newUser(){
        return "user-signup.html";
    }
    @PostMapping("/signup")
    public String checkRole(@RequestParam("username") String username,
                            @RequestParam("password") String password,
                            @RequestParam("reenterpassword") String reenterpassword, Model model
            , @RequestParam("role") String role){
        if(!password.equals(reenterpassword)){
            String msg = "Mật khẩu không khớp!";
            model.addAttribute("msg1",msg);
            return "user-signup.html";

        }else if(userService.findByUsername(username)!=null){
            String msg = "Tên đăng nhập đã tồn tại";
            model.addAttribute("msg2",msg);
            return "user-signup.html";
        }
        else{
            User user = new User();
            user.setUsername(username);
            user.setPassword(password);
            int user_role;
            if(role.equals("1")){
                user_role=1;
            }else if(role.equals("2")){
                user_role=2;
            }else{
                user_role=3;
            }
            user.setRole(user_role);
            userService.create(user);
            User newUser;
            newUser=userService.findByUsername(username);
            model.addAttribute("user_id",newUser.getUser_id());

            if(role.equals("1")){
                return "new-student.html";
            }else if(role.equals("2")){

                return "new-expert.html";
            }else{

                return "new-lecturer.html";
            }
        }

    }

}

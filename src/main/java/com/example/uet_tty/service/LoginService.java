package com.example.uet_tty.service;

import com.example.uet_tty.entity.User;
import com.example.uet_tty.repository.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LoginService {
    @Autowired
    UserRepo userRepo;
    public boolean check(String username, String password){
        User user = userRepo.findByUsername(username);
        if(user==null){
            return false;
        }
        return user.getPassword().equals(password);
    }
}

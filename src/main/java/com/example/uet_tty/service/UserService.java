package com.example.uet_tty.service;
import com.example.uet_tty.entity.User;
import com.example.uet_tty.repository.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;

@Service
public class UserService {
    @Autowired
    UserRepo userRepo;

    @Transactional
    public void create(User user){
        userRepo.save(user);
    }

    public ArrayList<User> getAll(){
        return (ArrayList<User>) userRepo.findAll();
    }

    @Transactional
    public void delete(int id){
        userRepo.deleteById(id);
    }

    //user chi co the update password hoac xoa tai khoan
    @Transactional
    public void updatePassword(User user){
        User currentUser = userRepo.findById(user.getUser_id()).orElse(null);
        if(currentUser!=null){
            currentUser.setUsername(user.getUsername());
            currentUser.setRole(user.getRole());
            currentUser.setPassword(user.getPassword());
            userRepo.save(currentUser);
        }
    }
    public User findByUsername(String username){
        return userRepo.findByUsername(username);
    }
}

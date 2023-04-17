package com.example.uet_tty.service;

import com.example.uet_tty.entity.Expert;
import com.example.uet_tty.repository.ExpertRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class ExpertService {
    @Autowired
    ExpertRepo expertRepo;
    public void create(Expert expert){
        expertRepo.save(expert);
    }

    public void update(Expert expert){
        Expert currentExpert = expertRepo.findById(expert.getExpert_id()).orElse(null);
        if(currentExpert!=null){
            expertRepo.save(expert);
        }
    }

    public void delete(int id){
        expertRepo.deleteById(id);
    }

    public ArrayList<Expert> showAll(){
        return (ArrayList<Expert>) expertRepo.findAll();
    }

    public ArrayList<Expert> searchName(String name){
        return expertRepo.searchByName(name);
    }

}

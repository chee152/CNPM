package com.example.uet_tty.service;


import com.example.uet_tty.entity.Lecturer;

import com.example.uet_tty.repository.LecturerRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LecturerService {
    @Autowired
    LecturerRepo lecturerRepo;
    public void create(Lecturer lecturer){
        lecturerRepo.save(lecturer);
    }

    public void update(Lecturer lecturer){
        Lecturer currentLecturer = lecturerRepo.findById(lecturer.getLecture_id()).orElse(null);
        if(currentLecturer!=null){
            lecturerRepo.save(lecturer);
        }
    }

    public void delete(int id){
        lecturerRepo.deleteById(id);
    }
}
